import jwtDecode from "jwt-decode";
import { IJwtContent } from "./constants/@types";

export class jwtUtils {
	static isAuth(token: string | null | undefined) {
		if (!token) return false;

		const decoded = jwtDecode<IJwtContent>(token);
		if (decoded.exp > new Date().getTime() / 1000) {
			return true;
		} else {
			return false;
		}
	}

	static getIdFromToken(token: string) {
		const decoded = jwtDecode<IJwtContent>(token);
		return decoded.sub;
	}

	static getContentFromToken(token: string) {
		const decoded = jwtDecode<IJwtContent>(token);
		return decoded;
	}
}
