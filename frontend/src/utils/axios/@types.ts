// * API REQUEST CLASS
export interface IRegisterRequest {
	email: string;
	password: string;
	nickname: string;
	address: string;
	sex: string;
	age: string;
	job: string;
	phoneNumber: string;
}

export interface IResponse {
	error: string;
	message: string;
	path: string;
	status: number;
	timestamp: Date;
	trace: string;
}

export interface ILoginResponse {
	jwt: string;
}

export interface IJwtContent {
	id: number;
	email: string;
	nickname: string;
}
