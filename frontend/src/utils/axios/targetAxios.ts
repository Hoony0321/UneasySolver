import { AxiosError } from "axios";
import axios from ".";
import { API_TARGET } from "../constants/api.constants";

export interface ITarget {
	id: number;
	name: string;
}

export const useTargetAxios = () => {
	const getTargetList = async () => {
		try {
			let result: ITarget[] = [];
			await axios.get(API_TARGET).then((response) => {
				result = response.data as ITarget[];
			});
			return result;
		} catch (error: unknown) {
			return "Target 값을 가져오는 중에 에러가 발생했습니다.";
		}
	};

	return { getTargetList };
};
