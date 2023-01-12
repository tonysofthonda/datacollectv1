package com.honda.hdm.datacollect.service.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;
import org.apache.camel.Exchange;
import org.apache.camel.dataformat.beanio.BeanIOHeader;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.spi.DataFormatName;
import org.apache.camel.support.ServiceSupport;
import org.apache.camel.util.IOHelper;
import org.apache.camel.util.ObjectHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.beanio.BeanReader;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Custom DataFormat based on BeanIODataFormat.
 * 
 * Useful when implementing Apache BeanIO + Apache Camel Routes
 * and explicitly wants to define Streams via annotations, instead of 
 * using XML mapping. 
 * 
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 * @since  Aug 28, 2018
 *
 */
@Component
public class DcDataFormat extends ServiceSupport implements DataFormat, DataFormatName, CamelContextAware {
    public static String dcDealerName;
    private static final Logger LOGGER = LogManager.getLogger(DcDataFormat.class);
    
    private DataCollectBeanIOMap dCollectBeanIOMap;

    private transient CamelContext camelContext;
    private transient StreamFactory factory;
    private String streamName;
    //private Charset encoding = Charset.defaultCharset();
    private Charset encoding = Charset.forName("UTF-8"); 
           
    @Autowired
    DcErrorCollector errorHandler;

    @Override
    public  String getDataFormatName() {
        return "beanio-datacollect";
    }

    @Override
    protected  void doStart() throws Exception {
        setStreamName(DataCollectBeanIOMap.STREAM_MAP_NAME);
        
        if (factory == null) {
            // Create the stream factory that will be used to read/write objects.
            dCollectBeanIOMap = new DataCollectBeanIOMap();
            dCollectBeanIOMap.initialize();
            factory = dCollectBeanIOMap.getIoDataCollectFactory();
        }
    }

    @Override
    protected  void doStop() throws Exception {
        factory = null;
    }

    @Override
    public  CamelContext getCamelContext() {
        return camelContext;
    }

    @Override
    public  void setCamelContext(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

     StreamFactory getFactory() {
        return factory;
    }

    @Override
    public  void marshal(Exchange exchange, Object body, OutputStream stream) throws Exception {
        List<Object> models = getModels(exchange, body);
        writeModels(stream, models);
    }

    @Override
    public  Object unmarshal(Exchange exchange, InputStream stream) throws Exception {
        return readModels(exchange, stream);
    }

    @SuppressWarnings("unchecked")
    private  List<Object> getModels(Exchange exchange, Object body) {
        List<Object> models;
        if ((models = exchange.getContext().getTypeConverter().convertTo(List.class, body)) == null) {
            models = new ArrayList<>();
            Iterator<Object> it = ObjectHelper.createIterator(body);
            while (it.hasNext()) {
                models.add(it.next());
            }
        }
        return models;
    }

    private  void writeModels(OutputStream stream, List<Object> models) {
        BufferedWriter streamWriter = IOHelper.buffered(new OutputStreamWriter(stream, getEncoding()));
        BeanWriter out = factory.createWriter(getStreamName(), streamWriter);

        for (Object obj : models) {
            out.write(obj);
        }

        out.flush();
        out.close();
    }

    private  List<Object> readModels(Exchange exchange, InputStream stream) throws Exception {
        List<Object> results = new ArrayList<>();
        BufferedReader streamReader = IOHelper.buffered(new InputStreamReader(stream, getEncoding()));
        
        BeanReader in = factory.createReader(getStreamName(), cleanReader(streamReader));
        in.setErrorHandler(errorHandler);
        
        try {
            Object readObject;
            while ((readObject = in.read()) != null) {                    
                if (readObject instanceof BeanIOHeader) {
                    exchange.getOut().getHeaders().putAll(((BeanIOHeader) readObject).getHeaders());
                }
                results.add(readObject);
            }
        } catch(Exception ex){
            LOGGER.error(ex);
        }
        finally {
            in.close();
        }

        return results;
    }
    
    /**
     * Clean the buffered reader to prevent issues with empty lines while reading
     * @param br BufferedReader with the original file content
     * @return A copy of the buffered reader without empty lines
     * @throws IOException 
     */
    private BufferedReader cleanReader(BufferedReader br) throws IOException{
        String line;
        StringBuilder clean;
        clean = new StringBuilder();
        
        while((line = br.readLine()) != null) {
            if(line.isEmpty()) {
                continue;
            }
            
            if(line.contains("\"")){
                line = line.replaceAll("\"", "");
            }
            
            if(line.contains("A0|")){
                List<String> A0values = Arrays.asList(line.split("\\|"));
                try{
                    dcDealerName = A0values.get(2);
                }
                catch(Exception e){
                     System.err.println("THE FILE HAS NO DELAER NAME");
                }
                
            }
         
            clean.append(line);            
            clean.append("\n");
        }
        //Remove the last \n char
        clean.deleteCharAt(clean.length() - 1);
        return new BufferedReader(new StringReader(clean.toString()));
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public Charset getEncoding() {
        return encoding;
    }

    public void setEncoding(Charset encoding) {
        this.encoding = encoding;
    }

}
