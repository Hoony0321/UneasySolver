import axios from ".";
import { API_JOB } from "../constants/api.constants";

export interface IJob {
	id: number;
	name: string;
}

export const useJobAxios = () => {
	const getJobList = async () => {
		try {
			let result: IJob[] = [];
			await axios.get(API_JOB).then((response) => {
				result = response.data as IJob[];
			});
			return result;
		} catch (error: unknown) {
			return "Job 값을 가져오는 중에 에러가 발생했습니다.";
		}
	};

	return { getJobList };
};
