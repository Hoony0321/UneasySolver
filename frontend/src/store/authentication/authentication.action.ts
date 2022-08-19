// import axios from "@utils/axios";
// import { API_LOGIN } from "@utils/constants/api.constants";
// import { AxiosResponse } from "axios";
// import { useRecoilState } from "recoil";
// import { ILoginResponseProps } from "./authentication.props";
// import { authenticationAtom } from "./authentication.state";

// export const useAuthenticationActions = () => {
// 	const [authState, setAuthState] = useRecoilState(authenticationAtom);

// 	const login = async ({
// 		email,
// 		password,
// 	}: {
// 		email: string;
// 		password: string;
// 	}) => {
// 		// axios
// 		// .post(API_LOGIN, { email: email, password: password })
// 		// .then(async (response) => {
// 		//     localStorage.setItem("token", (response.data as ILoginResponse).jwt);
// 		//     await router.push("/");
// 		//     location.reload();
// 		// })
// 		// .catch((error: AxiosError) => {
// 		//     console.log(error);

// 		//     const data: IResponse = error.response?.data as IResponse;
// 		//     console.log(data.message);
// 		//     onOpen();
// 		// });

// 		try {
// 			const response: AxiosResponse<ILoginResponseProps> = await axios.post(
// 				API_LOGIN,
// 				{ email, password },
// 			);
// 			if (response.status === 200) {
// 				// TODO 로그인 완료 시 처리

// 				setAuthState((old) => ({
// 					...old,
// 					isAuthenticated: true,
// 				}));

// 				return response.data;
// 			}
// 		} catch (error: unknown) {
// 			if (error instanceof Error) throw new Error(error.message);
// 		}
// 	};

// 	return {
// 		login,
// 	};
// };
