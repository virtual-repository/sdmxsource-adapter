package org.acme;

import static org.sdmxsource.sdmx.api.constants.STRUCTURE_OUTPUT_FORMAT.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Test;
import org.sdmx.SdmxServiceFactory;
import org.sdmxsource.sdmx.api.manager.output.StructureWritingManager;
import org.sdmxsource.sdmx.api.manager.parse.StructureParsingManager;
import org.sdmxsource.sdmx.api.model.StructureWorkspace;
import org.sdmxsource.sdmx.api.model.beans.SdmxBeans;
import org.sdmxsource.util.io.ReadableDataLocationTmp;

public class FactoryTest {

	@Test
	public void servicesAreAvailable() {

		InputStream in = getClass().getClassLoader().getResourceAsStream("samplesdmx.xml");

		StructureParsingManager parser = SdmxServiceFactory.parser();

		StructureWorkspace ws = parser.parseStructures(new ReadableDataLocationTmp(in));

		SdmxBeans beans = ws.getStructureBeans(false);

		StructureWritingManager writer = SdmxServiceFactory.writer();

		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

		writer.writeStructures(beans, SDMX_V21_STRUCTURE_DOCUMENT, out);

	}
}
