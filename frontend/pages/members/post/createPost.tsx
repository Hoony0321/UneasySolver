import {
	Box,
	Button,
	Circle,
	Flex,
	FormControl,
	FormLabel,
	Input,
	InputGroup,
	InputRightElement,
	Select,
	Text,
	Textarea,
} from "@chakra-ui/react";
import { NextPage } from "next";
import React, { useState } from "react";
import Container from "../../components/common/container";
import Wrapper from "../../components/common/wrapper";

const CreatePostPage: NextPage = () => {
	const [target, setTarget] = useState<string>("");

	return (
		<Wrapper py={24}>
			<Container w={{ base: "80%", lg: "65%" }} flexDir={"column"}>
				<form style={{ margin: "auto", width: "100%" }}>
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
									placeholder="(필수) 불편 대상"
									border="3px solid"
									borderColor="primary"
									fontSize="1.2em"
									color="gray.500"
									onChange={(e) => setTarget(e.target.value)}>
									<option value="식품">식품</option>
									<option value="패션">패션</option>
									<option value="생활">생활</option>
									<option value="여가">여가</option>
									<option value="서비스">서비스</option>
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
							<InputGroup size="lg" mt={4}>
								<Input
									type="text"
									placeholder="불편지수"
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
									placeholder="불편지수"
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
						<Flex
							justifyContent="space-between"
							borderBottom="2px solid"
							borderColor="primary"
							borderRadius={2}
							py={2}>
							<Flex>
								<Circle size={6} bgColor="primary" />
								<Text ml={3}>필수 불편내용 작성</Text>
							</Flex>
							<Text>+10point</Text>
						</Flex>

						<Flex
							justifyContent="space-between"
							borderBottom="2px solid"
							borderColor="primary"
							borderRadius={2}
							py={2}>
							<Flex>
								<Circle size={6} bgColor="primary" />
								<Text ml={3}>불편 지역 선택</Text>
							</Flex>
							<Text>+5point</Text>
						</Flex>

						<Flex
							justifyContent="space-between"
							borderBottom="2px solid"
							borderColor="primary"
							borderRadius={2}
							py={2}>
							<Flex>
								<Circle size={6} bgColor="primary" />
								<Text ml={3}>선택 사진 첨부</Text>
							</Flex>
							<Text>+5point</Text>
						</Flex>

						<Flex mx="auto" alignItems="center" gap={4} mt={12}>
							<Text fontWeight="bold" fontSize="1.5em">
								총 보상
							</Text>
							<Text fontWeight="bold" fontSize="1.8em" color="primary">
								0 Point
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
			</Container>
		</Wrapper>
	);
};

export default CreatePostPage;
