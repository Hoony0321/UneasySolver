import {
	Box,
	Button,
	Circle,
	Flex,
	FormControl,
	FormLabel,
	Input,
	InputGroup,
	Select,
	Text,
	Textarea,
	useDisclosure,
} from "@chakra-ui/react";
import { NextPage } from "next";
import React, { FormEvent, useEffect, useState } from "react";
import { RecoilValueReadOnly, useRecoilState, useRecoilValue } from "recoil";
import { authenticationAtom } from "../../../src/store/authentication/authentication.state";
import { usePostAxios } from "../../../src/utils/axios/postAxios";

import { useTargetAxios } from "../../../src/utils/axios/targetAxios";
import {
	IPostCreateRequest,
	ITarget,
} from "../../../src/utils/constants/@types";
import { AlarmModal, IModalState } from "../../components/common/alarmModal";
import Container from "../../components/common/container";
import Wrapper from "../../components/common/wrapper";
import RewardOpt from "../../components/post/createPost/rewardOpt";

import InputUneasyIndex from "../../components/post/createPost/uneasyIdx";

const CreatePostPage: NextPage = () => {
	const { id, token } = useRecoilValue(authenticationAtom);

	const [target, setTarget] = useState<number>(0);
	const [uneasyIdx, setUneasyIdx] = useState<number>(0);
	const [content, setContent] = useState<string>("");
	const [address, setAddress] = useState<string>(""); // * 선택
	const [imgFile, setImgFile] = useState<string>(""); // * 선택
	const [rewardOpt, setRewardOpt] = useState<boolean[]>([false, false, false]);
	const [reward, setReward] = useState<number>(0);

	const { createPost } = usePostAxios();

	const disclosure = useDisclosure();
	const [modalState, setModalState] = useState<IModalState>({
		title: "",
		msg: "",
		href: null,
	});

	// * UseEffect
	useEffect(() => {
		// * 필수 내용 작성 완료
		if (!rewardOpt[0] && target != 0 && uneasyIdx != 0 && content != "") {
			setRewardOpt((prev) => {
				const arr = [...prev];
				arr[0] = true;
				return arr;
			});
		}

		// * (선택) 지역 선택
		if (!rewardOpt[1] && address != "") {
			setRewardOpt((prev) => {
				const arr = [...prev];
				arr[1] = true;
				return arr;
			});
		}

		// * (선택) 사진 첨부
		if (!rewardOpt[2] && imgFile != "") {
			setRewardOpt((prev) => {
				const arr = [...prev];
				arr[2] = true;
				return arr;
			});
		}
	}, [target, uneasyIdx, content, address, imgFile]);

	useEffect(() => {
		if (rewardOpt[0]) {
			setReward((prev) => prev + 10);
		}

		if (rewardOpt[1]) {
			setReward((prev) => prev + 5);
		}

		if (rewardOpt[2]) {
			setReward((prev) => prev + 5);
		}
	}, [rewardOpt]);

	useEffect(() => {
		getTargetList()
			.then((res) => {
				if (res) {
					setTargetList(res as ITarget[]);
				}
			})
			.catch((err) => {
				console.log(err);
			});
	}, []);

	// useEffect(() => {
	// 	console.log("here");
	// 	console.log(modalState.disclosure);
	// 	modalState.disclosure.onOpen!();
	// }, [modalState]);

	// * Axios Target / Job
	const { getTargetList } = useTargetAxios();
	const [targetList, setTargetList] = useState<ITarget[]>([]);

	// * handle Function
	const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		console.log("submit 클릭");
		// * 필수 조건 다 안 채움
		if (!rewardOpt[0]) {
			disclosure.onOpen();
			setModalState((prev) => ({
				...prev,
				title: "에러",
				msg: "필수 입력 조건들을 다 채워주세요.",
				href: null,
			}));
			window.scrollTo(0, 0);
			return;
		}

		//* axios create post 전송
		console.log(target);
		createPost({
			id: id,
			title: "불편 제목",
			target: target,
			uneasyIdx: uneasyIdx,
			content: content,
			address: address,
			imgFile: imgFile,
			point: reward,
		} as IPostCreateRequest);

		window.location.replace("/");
	};

	return (
		<Wrapper py={24}>
			<Container w={{ base: "80%", lg: "65%" }} flexDir={"column"}>
				<form
					style={{ margin: "auto", width: "100%" }}
					onSubmit={(e) => handleSubmit(e)}>
					<Flex flexDir="column" gap={12}>
						<FormControl>
							<Flex flexDir="row" alignItems="center">
								<Circle
									bgColor="primary"
									color="white"
									size={8}
									textAlign="center">
									1
								</Circle>
								<FormLabel
									color="primary"
									fontWeight="bold"
									fontSize="1.2em"
									m={0}
									ml={2}>
									불편대상을 선택해주세요.
								</FormLabel>
							</Flex>
							<InputGroup size="lg" mt={4}>
								<Select
									required
									border="3px solid"
									borderColor="primary"
									fontSize="1.2em"
									color="black"
									onChange={(e) => setTarget(Number.parseInt(e.target.value))}>
									<option value="null" selected hidden>
										(필수) 불편 대상
									</option>
									{targetList.map((target) => (
										<option key={target.id} value={target.id}>
											{target.name}
										</option>
									))}
								</Select>
							</InputGroup>
						</FormControl>

						<FormControl>
							<Flex flexDir="row">
								<Circle
									bgColor="primary"
									color="white"
									size={8}
									textAlign="center">
									2
								</Circle>
								<FormLabel
									color="primary"
									fontWeight="bold"
									fontSize="1.2em"
									m={0}
									ml={2}>
									불편지수를 선택해주세요.
								</FormLabel>
							</Flex>
							<InputUneasyIndex setFunc={setUneasyIdx} />
						</FormControl>

						<FormControl mt={2}>
							<Flex flexDir="row">
								<Circle
									bgColor="primary"
									color="white"
									size={8}
									textAlign="center">
									3
								</Circle>
								<FormLabel
									color="primary"
									fontWeight="bold"
									fontSize="1.2em"
									m={0}
									ml={2}>
									불편 내용을 입력해주세요.
								</FormLabel>
							</Flex>
							<InputGroup size="lg" mt={4}>
								<Textarea
									placeholder="불편 내용"
									border="3px solid"
									borderColor="primary"
									fontSize="1.2em"
									value={content}
									onChange={(e) => setContent(e.target.value)}
									h={300}
								/>
							</InputGroup>
						</FormControl>

						<FormControl>
							<Flex flexDir="row">
								<Circle
									bgColor="primary"
									color="white"
									size={8}
									textAlign="center">
									4
								</Circle>
								<FormLabel
									color="primary"
									fontWeight="bold"
									fontSize="1.2em"
									m={0}
									ml={2}>
									불편 지역을 선택해주세요. (선택)
								</FormLabel>
							</Flex>
							<InputGroup size="lg" mt={4}>
								<Input
									type="text"
									placeholder="불편 지역"
									border="3px solid"
									borderColor="primary"
									fontSize="1.2em"
								/>
							</InputGroup>
						</FormControl>

						<FormControl>
							<Flex flexDir="row">
								<Circle
									bgColor="primary"
									color="white"
									size={8}
									textAlign="center">
									5
								</Circle>
								<FormLabel
									color="primary"
									fontWeight="bold"
									fontSize="1.2em"
									m={0}
									ml={2}>
									첨부할 사진을 선택해주세요. (선택)
								</FormLabel>
							</Flex>
							<InputGroup size="lg" mt={4}>
								<Input
									type="text"
									placeholder="불편 사진"
									border="3px solid"
									borderColor="primary"
									fontSize="1.2em"
								/>
							</InputGroup>
						</FormControl>
					</Flex>
					<Box
						border="2px solid"
						borderColor="primary"
						my={12}
						borderRadius={10}
					/>
					<Flex flexDir="column" gap={6}>
						<Text fontSize="1.5em" color="primary" fontWeight="bold">
							획득 가능 보상
						</Text>

						<RewardOpt
							text="필수 불편내용 작성"
							point={10}
							active={rewardOpt[0]}
						/>
						<RewardOpt text="불편 지역 선택" point={5} active={rewardOpt[1]} />
						<RewardOpt text="불편 사진 첨부" point={5} active={rewardOpt[2]} />

						<Flex mx="auto" alignItems="center" gap={4} mt={12}>
							<Text fontWeight="bold" fontSize="1.5em">
								총 보상
							</Text>
							<Text fontWeight="bold" fontSize="2em" color="primary">
								{reward} Point
							</Text>
						</Flex>
					</Flex>

					<Button
						type="submit"
						mt={16}
						size="lg"
						fontSize="1.2em"
						w="100%"
						variant="ghost"
						bgColor="primary"
						color="white">
						작성 완료
					</Button>
				</form>
				<AlarmModal state={modalState} disclosure={disclosure} />
			</Container>
		</Wrapper>
	);
};

export default CreatePostPage;
