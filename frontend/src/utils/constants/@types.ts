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
	code: number;
	message: string;
	success: boolean;
	data: unknown;
}

export interface ILoginResponse {
	jwt: string;
}

export interface IJwtContent {
	sub: number;
	email: string;
	auth: string;
	exp: number;
}

export interface ITarget {
	id: number;
	name: string;
}

export interface IPost {
	id: number;
	date: Date;
	title: string;
	target: string;
	content: string;
	tags: string[];
	author: string;
	hits: number;
	comments: number;
}

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
