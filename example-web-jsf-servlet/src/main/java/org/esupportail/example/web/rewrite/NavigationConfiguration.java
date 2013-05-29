package org.esupportail.example.web.rewrite;

import static org.esupportail.example.web.rewrite.NavigationRules.HOME;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Resource;
import org.ocpsoft.rewrite.servlet.config.Substitute;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

public class NavigationConfiguration extends HttpConfigurationProvider {

	@Override
	public int priority() {
		return 10;
	}

	@Override
	public Configuration getConfiguration(final ServletContext context) {
		final String ctx = context.getContextPath();
		return ConfigurationBuilder.begin()

				.addRule(Join.path(ctx + "/").to(ctx + HOME))
				
				.addRule()
				.when(Direction
						.isInbound()
						.and(Path.matches("/{objects}"))
						.and(Resource.exists(ctx + "/stylesheets/{objects}/list.xhtml")))
				.perform(Forward.to(ctx + "/stylesheets/{objects}/list.xhtml"))

				.addRule()
				.when(Direction
						.isInbound()
						.and(Path.matches(ctx + "/{objects}/{action}"))
						.and(Resource
								.exists(ctx + "/stylesheets/{objects}/{action}.xhtml")))
				.perform(Forward.to(ctx + "/stylesheets/{objects}/{action}.xhtml"))

				.addRule()
				.when(Direction
						.isInbound()
						.and(Path.matches(ctx + "/{objects}/{action}/{id}"))
						.and(Resource
								.exists(ctx + "/stylesheets/{objects}/{action}.xhtml")))
				.perform(Forward.to(ctx + "/stylesheets/{objects}/{action}.xhtml?pk={id}"))

				//////////////////////////////// OUTBOUND /////////////////////////////////

				// Outbound access rules

				.addRule()
				.when(Direction.isOutbound().and(
						Path.matches(ctx + "/stylesheets/{objects}/list.xhtml")))
				.perform(Substitute.with(ctx + "/{objects}"))

				.addRule()
				.when(Direction.isOutbound().and(
						Path.matches(ctx + "/stylesheets/{objects}/{action}.xhtml")))
				.perform(Substitute.with(ctx + "/{objects}/{action}"))
				
				;
	}
}
