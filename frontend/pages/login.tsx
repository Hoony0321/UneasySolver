import {
	Box,
	Button,
	Flex,
	FormControl,
	FormHelperText,
	FormLabel,
	Input,
	Text,
	useBoolean,
} from "@chakra-ui/react";
import { NextPage } from "next";
import Link from "next/link";
import React, { ChangeEvent, FormEvent, useEffect, useState } from "react";
import Container from "./components/common/container";
import Wrapper from "./components/common/wrapper";

const LoginPage: NextPage = () => {
	const [email, setEmail] = useState<string>("");
	const [password, setPassword] = useState<string>("");

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

		//! axios로 서버와 통신하기
	};

	//* 기타 메서드

	const moveSignUpPage = () => {
		console.log("회원가입 버튼 클릭");
	};

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
			</Container>
		</Wrapper>
	);
};

export default LoginPage;
