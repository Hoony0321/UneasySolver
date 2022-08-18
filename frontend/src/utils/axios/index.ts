import baseAxios from "axios";
import { API_BASE } from "../constants/api.constants";

const axios = baseAxios.create({
	baseURL: API_BASE,
	timeout: 8000,
});

export default axios;
