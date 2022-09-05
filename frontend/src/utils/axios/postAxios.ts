import axios from ".";
import { IPost, IPostCreateRequest } from "../constants/@types";
import { API_POST_CREATE, API_POST_LIST } from "../constants/api.constants";

export const usePostAxios = () => {
	const createPost = (data: IPostCreateRequest) => {
		try {
			axios
				.post(API_POST_CREATE, data, {
					headers: { auth: localStorage.getItem("token")! },
				})
				.then((res) => {
					console.log("Post Create Request 전송 성공했습니다.");
				})
				.catch((err) => console.log("error 발생"));
			return true;
		} catch (error: unknown) {
			console.log("post create request 전송 중에 문제가 발생했습니다.");
			return false;
		}
	};

	const getPostList = async () => {
		try {
			let result: IPost[] = [];
			await axios.get<IPost[]>(API_POST_LIST).then((res) => {
				result = res.data;
			});
			return result;
		} catch (error: unknown) {
			return "Post를 가져오는 중에 에러가 발생했습니다.";
		}
	};

	return { getPostList, createPost };
};
