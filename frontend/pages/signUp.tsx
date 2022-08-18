import {
	Box,
	Button,
	FormControl,
	FormHelperText,
	FormLabel,
	Input,
	InputGroup,
	InputRightElement,
	Select,
	Text,
	useDisclosure,
} from "@chakra-ui/react";
import { NextPage } from "next";
import React, { FormEvent, useEffect, useState } from "react";
import axios from "../src/utils/axios";
import { IRegisterRequest } from "../src/utils/axios/@types";
import {
	API_CHECK_DUPLICATED_EMAIL,
	API_CHECK_DUPLICATED_NICKNAME,
	API_REGISTER,
} from "../src/utils/constants/api.constants";
import Container from "./components/common/container";

import Wrapper from "./components/common/wrapper";
import AlarmModal from "./components/common/alarmModal";

const SignUpPage: NextPage = () => {
	const [email, setEmail] = useState<string>("");
	const [password, setPassword] = useState<string>("");
	const [confirmPW, setConfirmPw] = useState<string>("");
	const [nickname, setNickName] = useState<string>("");
	const [address, setAddress] = useState<string>("");

	const [age, setAge] = useState<string>("");
	const [sex, setSex] = useState<string>("");
	const [job, setJob] = useState<string>("");
	const [phoneNumber, setPhoneNumber] = useState<string>("");

	//* 에러 상태
	const [emailFormatError, setEmailFormatError] = useState<boolean>(false);
	const [pwFormatError, setPwFormatError] = useState<boolean>(false);
	const [confirmPwFormatError, setConfirmPwFormatError] =
		useState<boolean>(false);
	const [nicknameFormatError, setNickNameFormatError] =
		useState<boolean>(false);
	const [addressFormatError, setAddressFormatError] = useState<boolean>(false);
	const [phoneNumberFormatError, setPhoneNumberFormatError] =
		useState<boolean>(false);

	//* 에러 메세지
	const [emailErrorMsg, setEmailErrorMsg] = useState<string>("");
	const [pwErrorMsg, setPwErrorMsg] = useState<string>("");
	const [confirmPwErrorMsg, setConfirmPwErrorMsg] = useState<string>("");
	const [nicknameErrorMsg, setNickNameErrorMsg] = useState<string>("");
	const [addressErrorMsg, setAddressErrorMsg] = useState<string>("");
	const [phoneNumberErrorMsg, setPhoneNumberErrorMsg] = useState<string>("");

	//* 긍정 상태
	const [emailFormatAvailable, setEmailFormatAvailable] =
		useState<boolean>(false);
	const [nicknameFormatAvailable, setNickNameFormatAvailable] =
		useState<boolean>(false);

	//* 긍정 메세지
	const [emailAvailableMsg, setEmailAvailableMsg] = useState<string>("");
	const [nicknameAvailableMsg, setNicknameAvailableMsg] = useState<string>("");

	//* 정규식
	const regEmail =
		/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

	const regPW =
		/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{12,}$/;

	const regNickname = /^([a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{1,10}$/;

	const regPhoneNumber = /^[0-9]{3}-[0-9]{3,4}-[0-9]{4}/;

	//* effect 메서드 (useState 딜레이 현상 해결)
	useEffect(() => {
		if (email == "") return;

		if (regEmail.test(email) == false) {
			setEmailFormatAvailable(false);
			setEmailFormatError(true);
			setEmailErrorMsg("올바른 이메일 형식이 아닙니다.");
			return;
		}
		setEmailFormatError(false);
	}, [email]);

	useEffect(() => {
		if (password == "") return;

		if (regPW.test(password) == false) {
			setPwFormatError(true);
			setPwErrorMsg(
				"올바른 비밀번호 형식이 아닙니다.\n 최소 13자, 하나 이상의 대문자, 하나 이상의 소문자, 하나의 특수 문자가 포함되어야 합니다.",
			);
			return;
		}
		setPwFormatError(false);
	}, [password]);

	useEffect(() => {
		if (confirmPW != password) {
			setConfirmPwFormatError(true);
			setConfirmPwErrorMsg("비밀번호가 일치하지 않습니다.");
			return;
		}

		setConfirmPwFormatError(false);
	}, [confirmPW, password]);

	useEffect(() => {
		if (nickname == "") return;

		if (regNickname.test(nickname) == false) {
			setNickNameFormatAvailable(false);
			setNickNameFormatError(true);
			setNickNameErrorMsg(
				"올바른 닉네임 형식이 아닙니다.\n 닉네임은 한글, 영문, 숫자만 가능하며 2-10자리입니다.",
			);
			return;
		}

		setNickNameFormatError(false);
	}, [nickname]);

	useEffect(() => {
		if (phoneNumber == "") return;
		if (regPhoneNumber.test(phoneNumber) == false) {
			setPhoneNumberFormatError(true);
			setPhoneNumberErrorMsg(
				"올바른 핸드폰 번호 형식이 아닙니다.\n 하이픈 문자(-)를 포함하여 제대로 입력해주세요.",
			);
			return;
		}

		setPhoneNumberFormatError(false);
	}, [phoneNumber]);

	//* Handling 메서드
	const handleSubmit = (e: FormEvent) => {
		e.preventDefault();

		//에러 여부 확인
		if (
			emailFormatError ||
			pwFormatError ||
			confirmPwFormatError ||
			nicknameFormatError ||
			addressFormatError ||
			phoneNumberFormatError
		) {
			window.scrollTo(0, 0);
			return;
		}

		//필드 값 비워있는지 확인
		const emailEmpty = email == "";
		const passwordEmpty = password == "";
		const confirmPWEmpty = confirmPW == "";
		const nicknameEmpty = nickname == "";
		const addressEmpty = address == "";

		if (
			emailEmpty ||
			passwordEmpty ||
			confirmPWEmpty ||
			nicknameEmpty ||
			addressEmpty
		) {
			if (emailEmpty) {
				setEmailFormatError(true);
				setEmailErrorMsg("이메일을 입력해주세요.");
			}
			if (passwordEmpty) {
				setPwFormatError(true);
				setPwErrorMsg("비밀번호를 입력해주세요.");
			}
			if (confirmPWEmpty) {
				setConfirmPwFormatError(true);
				setConfirmPwErrorMsg("비밀번호 재확인을 입력해주세요.");
			}
			if (nicknameEmpty) {
				setNickNameFormatError(true);
				setNickNameErrorMsg("닉네임을 입력해주세요.");
			}
			if (addressEmpty) {
				setAddressFormatError(true);
				setAddressErrorMsg("주소를 입력해주세요.");
			}
			window.scrollTo(0, 0);
			return;
		}

		const request: IRegisterRequest = {
			email: email,
			password: password,
			nickname: nickname,
			address: address,
			sex: sex,
			age: age,
			job: job,
			phoneNumber: phoneNumber,
		};

		console.log(request);

		axios
			.post(API_REGISTER, request)
			.then((res) => console.log(res))
			.catch((error) => console.log(error));
	};

	//* 기타 메서드

	const checkDuplicatedEmail = () => {
		axios
			.get(API_CHECK_DUPLICATED_EMAIL, { params: { email: email } })
			.then((res) => {
				if (res.data) {
					setEmailFormatError(true);
					setEmailErrorMsg("기존에 회원가입된 이메일입니다.");
				} else {
					setEmailFormatError(false);
					setEmailFormatAvailable(true);
					setEmailAvailableMsg("사용가능한 이메일입니다.");
				}
			})
			.catch((res) => console.log(res));
	};

	const checkDuplicatedNickname = () => {
		axios
			.get(API_CHECK_DUPLICATED_NICKNAME, { params: { nickname: nickname } })
			.then((res) => {
				if (res.data) {
					setNickNameFormatError(true);
					setNickNameErrorMsg("존재하는 닉네임입니다..");
				} else {
					setNickNameFormatError(false);
					setNickNameFormatAvailable(true);
					setNicknameAvailableMsg("사용가능한 닉네임입니다.");
				}
			})
			.catch((res) => console.log(res));
	};

	const findAddress = () => {
		console.log("주소 찾기");
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
					회원가입
				</Text>
				<form onSubmit={(e) => handleSubmit(e)} style={{ width: "60%" }}>
					<FormControl isInvalid={emailFormatError} mt={12}>
						<FormLabel color="primary" fontWeight="bold" fontSize="1.2em">
							이메일
						</FormLabel>
						<InputGroup size="lg">
							<Input
								type="text"
								value={email}
								onChange={(e) => setEmail(e.target.value)}
								placeholder="이메일"
								border="3px solid"
								borderColor="primary"
								fontSize="1.2em"
							/>
							<InputRightElement width="4.5em" mr={2}>
								<Button
									disabled={emailFormatError}
									variant="ghost"
									color="primary"
									onClick={checkDuplicatedEmail}
									_hover={{ cursor: "pointer " }}>
									중복 확인
								</Button>
							</InputRightElement>
						</InputGroup>

						{emailFormatError && (
							<FormHelperText color="red.400">{emailErrorMsg}</FormHelperText>
						)}
						{emailFormatAvailable && (
							<FormHelperText color="green.500">
								{emailAvailableMsg}
							</FormHelperText>
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
							<FormHelperText color="red.400" whiteSpace="pre-wrap">
								{pwErrorMsg}
							</FormHelperText>
						)}
					</FormControl>
					<FormControl isInvalid={confirmPwFormatError} mt={6}>
						<FormLabel color="primary" fontWeight="bold" fontSize="1.2em">
							비밀번호 재확인
						</FormLabel>
						<Input
							type="password"
							value={confirmPW}
							onChange={(e) => setConfirmPw(e.target.value)}
							placeholder="비밀번호"
							border="3px solid"
							borderColor="primary"
							fontSize="1.2em"
							size="lg"
						/>
						{confirmPwFormatError && (
							<FormHelperText color="red.400">
								{confirmPwErrorMsg}
							</FormHelperText>
						)}
					</FormControl>
					<FormControl isInvalid={nicknameFormatError} mt={6}>
						<FormLabel color="primary" fontWeight="bold" fontSize="1.2em">
							닉네임
						</FormLabel>
						<InputGroup size="lg">
							<Input
								type="text"
								value={nickname}
								onChange={(e) => setNickName(e.target.value)}
								placeholder="닉네임"
								border="3px solid"
								borderColor="primary"
								fontSize="1.2em"
							/>
							<InputRightElement width="4.5em" mr={2}>
								<Button
									disabled={nicknameFormatError}
									variant="ghost"
									color="primary"
									onClick={checkDuplicatedNickname}
									_hover={{ cursor: "pointer " }}>
									중복 확인
								</Button>
							</InputRightElement>
						</InputGroup>

						{nicknameFormatError && (
							<FormHelperText color="red.400" whiteSpace="pre-wrap">
								{nicknameErrorMsg}
							</FormHelperText>
						)}
						{nicknameFormatAvailable && (
							<FormHelperText color="green.500" whiteSpace="pre-wrap">
								{nicknameAvailableMsg}
							</FormHelperText>
						)}
					</FormControl>
					<FormControl isInvalid={addressFormatError} mt={6}>
						<FormLabel color="primary" fontWeight="bold" fontSize="1.2em">
							주소
						</FormLabel>
						<InputGroup size="lg">
							<Input
								type="text"
								value={address}
								onChange={(e) => setAddress(e.target.value)}
								placeholder="주소"
								border="3px solid"
								borderColor="primary"
								fontSize="1.2em"
							/>
							<InputRightElement width="4.5em" mr={2}>
								<Button
									variant="ghost"
									color="primary"
									onClick={findAddress}
									_hover={{ cursor: "pointer " }}>
									주소 찾기
								</Button>
							</InputRightElement>
						</InputGroup>

						{addressFormatError && (
							<FormHelperText color="red.400">{addressErrorMsg}</FormHelperText>
						)}
					</FormControl>

					<Text fontWeight="bold" fontSize="1.2em" mt={20} color="primary">
						선택 입력 사항
					</Text>
					<Box width="100%" borderWidth="2px" borderColor="primary" />

					<FormControl mt={6}>
						<FormLabel color="primary" fontWeight="bold" fontSize="1.2em">
							성별
						</FormLabel>
						<InputGroup size="lg">
							<Select
								placeholder="(선택) 성별"
								border="3px solid"
								borderColor="primary"
								fontSize="1.2em"
								color="gray.500"
								onChange={(e) => setSex(e.target.value)}>
								<option value={"남"}>남</option>
								<option value={"여"}>여</option>
							</Select>
						</InputGroup>
					</FormControl>

					<FormControl mt={6}>
						<FormLabel color="primary" fontWeight="bold" fontSize="1.2em">
							나이
						</FormLabel>
						<InputGroup size="lg">
							<Input
								type="number"
								value={age}
								onChange={(e) => setAge(e.target.value)}
								placeholder="(선택) 나이"
								border="3px solid"
								borderColor="primary"
								fontSize="1.2em"
							/>
						</InputGroup>
					</FormControl>

					<FormControl mt={6}>
						<FormLabel color="primary" fontWeight="bold" fontSize="1.2em">
							직업
						</FormLabel>
						<InputGroup size="lg">
							<Select
								placeholder="(선택) 직업"
								border="3px solid"
								borderColor="primary"
								fontSize="1.2em"
								color="gray.500"
								onChange={(e) => setJob(e.target.value)}>
								<option value="직업1">직업1</option>
								<option value="직업2">직업2</option>
								<option value="직업3">직업3</option>
								<option value="직업4">직업4</option>
								<option value="직업5">직업5</option>
								<option value="직업6">직업6</option>
								<option value="직업7">직업7</option>
								<option value="직업8">직업8</option>
								<option value="직업9">직업9</option>
							</Select>
						</InputGroup>
					</FormControl>

					<FormControl mt={6}>
						<FormLabel color="primary" fontWeight="bold" fontSize="1.2em">
							핸드폰 번호
						</FormLabel>
						<InputGroup size="lg">
							<Input
								isInvalid={phoneNumberFormatError}
								type="text"
								value={phoneNumber}
								onChange={(e) => setPhoneNumber(e.target.value)}
								placeholder="(선택) 핸드폰 번호"
								border="3px solid"
								borderColor="primary"
								fontSize="1.2em"
							/>
						</InputGroup>
						{phoneNumberFormatError && (
							<FormHelperText color="red.400" whiteSpace="pre-wrap">
								{phoneNumberErrorMsg}
							</FormHelperText>
						)}
					</FormControl>

					<Button
						type="submit"
						mt={16}
						size="lg"
						fontSize="1.2em"
						w="100%"
						variant="ghost"
						bgColor="primary"
						color="white">
						회원가입
					</Button>
				</form>
			</Container>
		</Wrapper>
	);
};

export default SignUpPage;
