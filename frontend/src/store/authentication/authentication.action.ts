import { AxiosError } from "axios";
import { useRecoilState } from "recoil";
import axios from "../../utils/axios";
import { IJwtContent, ILoginResponse } from "../../utils/axios/@types";
import { API_LOGIN } from "../../utils/constants/api.constants";
import { jwtUtils } from "../../utils/jwtUtils";
import { authenticationAtom } from "./authentication.state";

export const useAuthenticationActions = () => {
	const [authState, setAuthState] = useRecoilState(authenticationAtom);

	const login = async ({
		email,
		password,
	}: {
		email: string;
		password: string;
	}) => {
		try {
			await axios
				.post<ILoginResponse>(API_LOGIN, { email: email, password: password })
				.then((response) => {
					const token = response.data.jwt; //토큰 가져오기
					localStorage.setItem("token", token); //토큰 로컬 스토리지 저장

					const userInfo = jwtUtils.getContentFromToken(token); //토큰에서 정보 추출
					setAuthState({
						//유저 정보 전역 변수에 저장
						token: token,
						id: userInfo.id,
						email: userInfo.email,
						nickname: userInfo.nickname,
					});

					return true;
				});
		} catch (error: unknown) {
			return false;
		}
	};

	const logout = () => {
		try {
			localStorage.removeItem("token");
			return true;
		} catch (error: unknown) {
			return false;
		}
	};

	const refreshInfo = () => {
		try {
			const token = localStorage.getItem("token");
			const tokenInfo = jwtUtils.getContentFromToken(token!);
			setAuthState(() => ({
				token: token!,
				id: tokenInfo.id,
				email: tokenInfo.email,
				nickname: tokenInfo.nickname,
			}));
		} catch (error: unknown) {
			console.log("에러 발생");
		}
	};

	return {
		login,
		logout,
		refreshInfo,
	};
};
