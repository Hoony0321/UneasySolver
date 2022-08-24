import { throws } from "assert";
import axios from ".";
import { API_POST_CREATE } from "../constants/api.constants";

export interface IPostState {
	target: number;
	uneasyIdx: number;
	content: string;
	address: string | null;
	imgFile: string | null;
}

export interface IPostCreateRequest extends IPostState {
	id: number;
	point: number;
}

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

	return { createPost };
};
