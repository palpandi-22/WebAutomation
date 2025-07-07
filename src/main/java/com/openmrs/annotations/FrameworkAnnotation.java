package com.openmrs.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.openmrs.enums.AuthorType;
import com.openmrs.enums.CategoryType;
import com.openmrs.enums.ProjectType;

/**
 * 
 * @author palpandi
 *         The {@code FrameworkAnnotation} annotation is used to mark methods as
 *         part of a framework. It provides information about the authors,
 *         categories, and modules to which the method belongs.
 *
 *         This annotation is retained at runtime, allowing reflection to access
 *         its values.
 *
 *         Usage: To use this annotation, you should annotate a method with it,
 *         providing information about the authors, categories, and modules to
 *         which the method belongs.
 * 
 *         <pre>
 * {@code
 * @FrameworkAnnotation(author = { AuthorType.JOHN, AuthorType.MARY }, category = { CategoryType.CORE,
 * 		CategoryType.UI }, module = { ModuleType.MODULE_A })
 * public void someFrameworkMethod() {
 * 	// Method implementation
 * }
 * }
 * </pre>
 *
 * @see AuthorType
 * @see CategoryType
 * @see ProjectType
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface FrameworkAnnotation {

	/**
	 * The authors of the annotated method.
	 *
	 * @return An array of author types.
	 */
	public AuthorType[] author();

	/**
	 * The categories to which the annotated method belongs.
	 *
	 * @return An array of category types.
	 */
	public CategoryType[] category();

	/**
	 * The modules to which the annotated method belongs.
	 *
	 * @return An array of module types.
	 */
	public ProjectType[] module();
}

