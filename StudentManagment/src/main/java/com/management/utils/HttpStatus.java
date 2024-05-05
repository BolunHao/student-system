package com.management.utils;

/**
 * Return status code
 * @author Wenqi Wang
 * @version 1.0
 * @since 2024-04-22
 */
public class HttpStatus
{
    /**
     * operate successfully
     */
    public static final int SUCCESS = 200;

    /**
     * Object created successfully
     */
    public static final int CREATED = 201;

    /**
     * The request has been accepted
     */
    public static final int ACCEPTED = 202;

    /**
     * The operation was performed successfully, but no data was returned
     */
    public static final int NO_CONTENT = 204;

    /**
     * The resource has been removed
     */
    public static final int MOVED_PERM = 301;

    /**
     * redirection
     */
    public static final int SEE_OTHER = 303;

    /**
     * The resource is not modified
     */
    public static final int NOT_MODIFIED = 304;

    /**
     * Parameter list error (missing, format mismatch)
     */
    public static final int BAD_REQUEST = 400;

    /**
     * unauthorized
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * Access restricted, authorization expired
     */
    public static final int FORBIDDEN = 403;

    /**
     * Resource, service not found
     */
    public static final int NOT_FOUND = 404;

    /**
     * http methods not allowed
     */
    public static final int BAD_METHOD = 405;

    /**
     * Resources conflict, or resources are locked
     */
    public static final int CONFLICT = 409;

    /**
     * Unsupported data, media type
     */
    public static final int UNSUPPORTED_TYPE = 415;

    /**
     * Internal system error
     */
    public static final int ERROR = 500;

    /**
     * Interface not implemented
     */
    public static final int NOT_IMPLEMENTED = 501;
}
