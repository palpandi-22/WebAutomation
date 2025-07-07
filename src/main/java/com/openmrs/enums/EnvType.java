package com.openmrs.enums;

/**
 * 
 * @author Palpandi
 * 
 * The {@code EnvType} enum represents different environment types typically used in software development and testing.
 * These predefined environment types can be used to specify the environment in which an application is deployed or tested.
 *
 * Usage:
 * <pre>
 * {@code
 * // Specify the environment type for your application or test
 * EnvType environment = EnvType.PRODUCTION_EU;
 *
 * // Use the environment type in your code for configuration or testing purposes
 * String apiUrl = Configuration.getApiUrl(environment);
 * }
 * </pre>
 */
public enum EnvType {
    OPENMRS,
}
 
