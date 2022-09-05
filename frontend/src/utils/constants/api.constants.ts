import { type } from "os";

// * Common
export const API_BASE = "http://localhost:8080";

// * Authentication
export const API_LOGIN = "/api/login";
export const API_REGISTER = "/api/register";
export const API_CHECK_DUPLICATED_EMAIL = "/api/members/check/email";
export const API_CHECK_DUPLICATED_NICKNAME = "/api/members/check/nickname";

//* Axios
export const API_TARGET = "/api/targets";
export const API_JOB = "/api/jobs";
export const API_POST_CREATE = "/api/auth/posts/create";
export const API_POST_LIST = "/api/posts";
export const API_AUTH = "/api/auth";
