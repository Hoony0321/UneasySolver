import {
	Box,
	Button,
	Flex,
	FormControl,
	FormHelperText,
	FormLabel,
	Input,
	Text,
	useDisclosure,
} from "@chakra-ui/react";
import { AxiosError } from "axios";

import { NextPage } from "next";
import Link from "next/link";
import { useRouter } from "next/router";
import React, { FormEvent, useEffect, useState } from "react";
import { useRecoilState } from "recoil";
import { authenticationAtom } from "../src/store/authentication/authentication.state";
import axios from "../src/utils/axios";
import { ILoginResponse, IResponse } from "../src/utils/axios/@types";
import { API_LOGIN } from "../src/utils/constants/api.constants";
import { jwtUtils } from "../src/utils/jwtUtils";
import { AlarmModal, IModalState } from "./components/common/alarmModal";
import Container from "./components/common/container";
import Wrapper from "./components/common/wrapper";

const LoginPage: NextPage = () => {
	const router = useRouter();
	const [authState, setAuthState] = useRecoilState(authenticationAtom);
	const disclosure = useDisclosure();

	const [email, setEmail] = useState<string>("");
	const [password, setPassword] = useState<string>("");

	//* modal 상태
	const [modalState, setModalState] = useState<IModalState>({
		title: "",
		msg: "",
	});

	//* 에러 상태
	const [emailFormatError, setEmailFormatError] = useState<boolean>(false);
	const [pwFormatError, setPwFormatError] = useState<boolean>(false);

	//* 에러 메세지
	const [emailErrorMsg, setEmailErrorMsg] = useState<string>("");
	const [pwErrorMsg, setPwErrorMsg] = useState<string>("");

	//* 정규식
	const regEmail =
		/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

	//* useEffect (error 핸들링)
	useEffect(() => {
		if (email == "") return;

		if (regEmail.test(email) == false) {
			setEmailFormatError(true);
			setEmailErrorMsg("올바른 이메일 형식이 아닙니다.");
			return;
		}

		setEmailFormatError(false);
	}, [email]);

	const handleSubmit = (e: FormEvent) => {
		e.preventDefault();

		//root 계정은 형식 무시 가능.
		if (email != "root") {
			//에러 발생 여부 확인
			if (emailFormatError || pwFormatError) return;

			//필드 값 비워있는지 확인
			const emailEmpty = email == "";
			const passwordEmpty = password == "";
			if (emailEmpty || passwordEmpty) {
				if (emailEmpty) {
					setEmailFormatError(true);
					setEmailErrorMsg("이메일을 입력해주세요.");
				}
				if (passwordEmpty) {
					setPwFormatError(true);
					setPwErrorMsg("비밀번호를 입력해주세요.");
				}
				return;
			}
		}

		axios
			.post(API_LOGIN, { email: email, password: password })
			.then(async (response) => {
				const token = (response.data as ILoginResponse).jwt;
				const content = jwtUtils.getContentFromToken(token);

				localStorage.setItem("token", (response.data as ILoginResponse).jwt);

				setAuthState({
					token: token,
					id: content.id,
					email: content.email,
					nickname: content.nickname,
				});

				await router.push("/");
			})
			.catch((error: AxiosError) => {
				const data: IResponse = error.response?.data as IResponse;
				setModalState((prev) => ({
					...prev,
					title: error.status!,
					msg: error.message.toString(),
				}));
				disclosure.onOpen();
			});
	};

	//* 기타 메서드

	return (
		<Wrapper py={32}>
			<Container
				flexDir="column"
				w="80%"
				maxW="container.md"
				border="3px solid"
				borderColor="primary"
				alignItems="center"
				py={16}
				borderRadius={10}>
				<Text fontSize="3em" fontWeight="bold" color="primary">
					해결사
				</Text>
				<form onSubmit={(e) => handleSubmit(e)} style={{ width: "60%" }}>
					<FormControl isInvalid={emailFormatError} mt={12}>
						<FormLabel color="primary" fontWeight="bold" fontSize="1.2em">
							이메일
						</FormLabel>
						<Input
							type="text"
							value={email}
							onChange={(e) => setEmail(e.target.value)}
							placeholder="이메일"
							border="3px solid"
							borderColor="primary"
							fontSize="1.2em"
							size="lg"
						/>
						{emailFormatError && (
							<FormHelperText color="red.400">{emailErrorMsg}</FormHelperText>
						)}
					</FormControl>

					<FormControl isInvalid={pwFormatError} mt={6}>
						<FormLabel color="primary" fontWeight="bold" fontSize="1.2em">
							비밀번호
						</FormLabel>
						<Input
							type="password"
							value={password}
							onChange={(e) => setPassword(e.target.value)}
							placeholder="비밀번호"
							border="3px solid"
							borderColor="primary"
							fontSize="1.2em"
							size="lg"
						/>
						{pwFormatError && (
							<FormHelperText color="red.400">{pwErrorMsg}</FormHelperText>
						)}
					</FormControl>

					<Flex flexDir="row-reverse" mt={2}>
						<Link href="#">
							<Text color="primary" _hover={{ cursor: "pointer" }}>
								이메일/비밀번호 찾기 {">"}{" "}
							</Text>
						</Link>
					</Flex>

					<Button
						type="submit"
						mt={10}
						size="lg"
						fontSize="1.2em"
						w="100%"
						variant="ghost"
						bgColor="primary"
						color="white">
						로그인
					</Button>
					<Box w="100%" border="1px solid" borderColor="primary" my={4} />
					<Link href="/signUp">
						<Button
							type="button"
							size="lg"
							fontSize="1.2em"
							w="100%"
							variant="outline"
							borderWidth="3px"
							borderColor="primary"
							color="primary">
							회원가입
						</Button>
					</Link>
				</form>

				<AlarmModal state={modalState} disclosure={disclosure} href={null} />
			</Container>
		</Wrapper>
	);
};

export default LoginPage;
