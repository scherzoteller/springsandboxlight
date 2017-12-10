package com.scherzoteller.springsandbox.web.utils.jamon;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import com.jamonapi.MonitorFactory;

/**
 * Persister for JAMon reports.
 * Stores the JAMon report at shutdown of the application (when Spring context is closed)
 *
 * @author eballet-baz
 */
public class JAMonPersister implements DisposableBean {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(JAMonPersister.class);

    private static final char SEPARATOR = ';';

    private static final String NEW_LINE = IOUtils.LINE_SEPARATOR;

    /**
     * @see org.springframework.beans.factory.DisposableBean#destroy()
     */
    @Override
    public void destroy() throws Exception {
        // Get report datas
        String[] header = MonitorFactory.getHeader();
        Object[][] body = MonitorFactory.getData();

        // Output report as CSV format
        StringBuilder report = new StringBuilder();
        report.append(StringUtils.join(header, SEPARATOR)).append(NEW_LINE);
        for (int i = 0; i < body.length; i++) {
            report.append(StringUtils.join(body[i], SEPARATOR)).append(NEW_LINE);
        }
        LOGGER.info(report.toString());
    }
}
