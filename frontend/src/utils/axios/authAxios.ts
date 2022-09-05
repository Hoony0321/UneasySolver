import axios from ".";
import { IResponse } from "../constants/@types";
import { API_AUTH } from "../constants/api.constants";

export const useAuthAxios = () => {
	const getAccessAuth = async (token: string) => {
		console.log("전달받은 토큰 : ", token);
		let result = false;
		try {
			await axios
				.get<IResponse>(API_AUTH, {
					headers: { Authorization: `Bearer ${token}` },
				})
				.then((response) => {
					console.log(response.data);
					if (response.data.success) {
						console.log("토큰 인증 성공");
						result = true;
					} else {
						console.log("토큰 인증 실패");
						result = false;
					}
				});
		} catch (error: unknown) {
			result = false;
		}

		return result;
	};

	return { getAccessAuth };
};
