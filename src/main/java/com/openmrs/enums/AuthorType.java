package com.openmrs.enums;

/**
 * 
 * @author Palpandi
 * 
 * The {@code AuthorType} enum represents different authors involved in a project or system.
 * These are predefined author names that can be associated with various components of the project.
 *
 * Usage:
 * <pre>
 * {@code
 * // Assign an author to a component or method
 * AuthorType author = AuthorType.PALPANDI;
 *
 * // Use the author in documentation or metadata
 * @FrameworkAnnotation(author = AuthorType.PALPANDI, ...)
 * }
 * </pre>
 */
public enum AuthorType {
    PALPANDI,
    AUTOMATION_USER
}

