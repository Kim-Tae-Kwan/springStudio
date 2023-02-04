package com.ktk.springStudio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Identity;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaType;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import com.ktk.springStudio.utils.JavaFileUtil;

class SpringStudioApplicationTests {

	@Test
	void contextLoads() {
		JavaFileUtil fileUtil = new JavaFileUtil();
		final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
		javaClass.setPackage("com.ktk.springStudio").setName("Member");

		javaClass.addField()
		  .setName("serialVersionUID")
		  .setType("long")
		  .setLiteralInitializer("1L")
		  .setPrivate()
		  .setStatic(true)
		  .setFinal(true);

		javaClass.addProperty(Integer.class, "id").setMutable(false);
		javaClass.addProperty(String.class, "firstName");
		javaClass.addProperty("String", "lastName");

		javaClass.addMethod()
		  .setConstructor(true)
		  .setPublic()
		  .setBody("this.id = id;")
		  .addParameter(Integer.class, "id");
		
		JavaClassSource member = Roaster.create(JavaClassSource.class);
		member.setPackage("com.ktk.springStudio.domain.entity").setName("Member");
		member.addAnnotation(Entity.class);
//		member.addProperty(Long.class, "id");
//		member.addProperty(String.class, "name");
//		member.addProperty(String.class, "password");
		
		member.addField()
			.setPrivate()
			.setType(Long.class)
			.setName("id")
			.addAnnotation(Id.class)
			.getOrigin()
			.addAnnotation(GeneratedValue.class);
//			.setLiteralValue("strategy", Identity.class.get)
		
		
		member.addField()
			.setPrivate()
			.setType(String.class)
			.setName("name")
			.addAnnotation(Column.class)
			.setLiteralValue("length", "100")
			.setLiteralValue("nullable", "false");
		
		member.addField()
			.setPrivate()
			.setType(String.class)
			.setName("password")
			.addAnnotation(Column.class)
			.setLiteralValue("length", String.valueOf(100))
			.setLiteralValue("nullable", String.valueOf(false));
		
		fileUtil.save("test123.java", member.toString());
	}

}
