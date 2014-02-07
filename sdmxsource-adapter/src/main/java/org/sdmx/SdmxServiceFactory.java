package org.sdmx;

import javax.inject.Inject;

import org.sdmxsource.sdmx.api.manager.output.StructureWriterManager;
import org.sdmxsource.sdmx.api.manager.parse.StructureParsingManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * A factory of SDMX services.
 * <p>
 * This is a utility class that:
 * 
 * <ul>
 * <li>bootstraps the Spring container required by MetadataTechnology SdmxSource, without any XML configuration
 * <li>makes available SdmxSource services from the Spring container, to DI-agnostic clients as well as CDI-enabled
 * clients
 * </ul>
 * 
 * 
 * @author Fabio Simeoni
 * 
 */
public class SdmxServiceFactory {

	// single instance injected by Spring via programmatic contanier configuration
	private static SdmxServiceFactory instance;
	
	private static ClassPathXmlApplicationContext ctx;

	// upon first access to this factory, Spring is configured programmatically
	static {
		init();
	}

	public static void init() {

		ctx = new ClassPathXmlApplicationContext("/sdmxsource-config.xml");

		// fetch singleton instance of this factory
		instance = ctx.getBean(SdmxServiceFactory.class);
	}

	// no other instances available to clients
	SdmxServiceFactory() {
	}

	@Inject
	StructureParsingManager parser;

	@Inject
	StructureWriterManager writer;

	// add more services if and when needed...

	// factory methods for generic clients and CDI clients

	/**
	 * Returns a {@link StructureParsingManager} service.
	 * 
	 * @return the service
	 */
	public static StructureParsingManager parser() {
		return instance.parser;
	}

	/**
	 * Returns a {@link StructureWritingManager} service.
	 * 
	 * @return the service
	 */
	public static StructureWriterManager writer() {
		return instance.writer;
	}
	
	
	public static void close() {
		
		ctx.close();
		
	}
}
