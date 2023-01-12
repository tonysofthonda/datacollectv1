package com.honda.hdm.datacollect.api.camel;

import com.honda.hdm.datacollect.service.config.CustomCfgProperties;
import com.honda.hdm.datacollect.service.csv.DcDataFormat;
import com.honda.hdm.datacollect.service.csv.DcFileInProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A Camel route for reading DataCollect input interface files.
 *
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 * @since Aug 28, 2018
 *
 */
@Component
public class DataCollectCamelRouter extends RouteBuilder {

    public static final String ROUTE_NAME = "DATA_COLLECT_ROUTE";

    @Autowired
    CustomCfgProperties customCfgProperties;

    @Autowired
    DcDataFormat dataFormat;

    @Autowired
    DcFileInProcessor dataCollectFileInProcessor;

    @Override
    public void configure() throws Exception {

        // Data Format when wanted to use Annotations and BeanIO Stream formed programatically
        // advantage: To not depend on a XML file existing on a specific classpath while using modular projects (api, service, web, ...)
        //DataFormat dataFormat = new DataCollectDataFormat();
        // Data Format when reading mapping directly from an XML file
        //BeanIODataFormat dataFormat = new BeanIODataFormat(BEANIO_MAPPING_FILE, DATA_COLLECT_STREAM_NAME);
        //dataFormat.setBeanReaderErrorHandler(new DataCollectErrorHandler());
        from(createIncomeURI())
                .routeId(ROUTE_NAME)
                .unmarshal(dataFormat)
                .process(dataCollectFileInProcessor)
                .onException(Exception.class).handled(true);
    }

    private String createIncomeURI() {
        String inputPath = customCfgProperties.getDataCollectConfig().getInterfacePathIn();

        String delay = "2000";
        //search files recursively into sub-dirs
        String recursive = "false";
        //move file to a sub-dir when processing //"&preMove=/tmp/staging/${file:name}"
        //TODO: check what if this option is used, error and done path does not work with absolute paths
        String preMove = "working";
        //move file to a distinct dir when eror
        String moveFailed = getErrorPath();
        //move file to a distinct dir
        String move = getOutputPath();

        return "file:///" + inputPath
                + "?delay=" + delay
                + "&recursive=" + recursive
                + "&preMove=" + preMove
                + "&move=" + move
                + "&moveFailed=" + moveFailed;
    }

    private String getErrorPath() {
        return customCfgProperties.getDataCollectConfig().getInterfacePathError() != null ? customCfgProperties.getDataCollectConfig().getInterfacePathError() : "..\\..\\error";
    }

    private String getOutputPath() {
        String outputPath = customCfgProperties.getDataCollectConfig().getInterfacePathDone() != null ? customCfgProperties.getDataCollectConfig().getInterfacePathDone() : "..\\..\\done";
        return outputPath;
    }
}
