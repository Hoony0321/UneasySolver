/* eslint-disable react/no-children-prop */
import {
	Button,
	Flex,
	Input,
	InputGroup,
	InputLeftElement,
	SimpleGrid,
} from "@chakra-ui/react";
import React, { useEffect, useState } from "react";
import Container from "../common/container";
import Wrapper from "../common/wrapper";

import { ImSad, ImKey } from "react-icons/im";
import { SearchIcon } from "@chakra-ui/icons";

import { LOREM_TEXT } from "../../../src/utils/constants/string.constants";
import Post from "./post";
import { IPost } from "../../../src/utils/constants/@types";
import { usePostAxios } from "../../../src/utils/axios/postAxios";

const SelectBox = () => {
	return (
		<Flex flexDir="row" justifyContent="space-between">
			<Flex flexDir="row" gap={6}>
				<Button
					leftIcon={<ImSad fontSize={"1.2em"} />}
					variant="ghost"
					color="primary"
					size="lg"
					fontSize={"1.5em"}>
					불편
				</Button>
				<Button
					leftIcon={<ImKey fontSize={"1.2em"} />}
					variant="ghost"
					color="primary"
					size="lg"
					fontSize={"1.5em"}>
					해결
				</Button>
			</Flex>
			<InputGroup color="primary" w="30%">
				<InputLeftElement children={<SearchIcon fontSize={"1.5em"} />} />
				<Input
					type="text"
					placeholder="불편 게시글 검색하기"
					fontSize={"1.5em"}
					border="none"
					borderRadius="none"
					borderBottom="2px solid"
					borderColor="orange"
					_placeholder={{ color: "primary" }}
					ml={2}
				/>
			</InputGroup>
		</Flex>
	);
};

const Section3 = () => {
	const [postList, setPostList] = useState<IPost[]>([]);
	const { getPostList } = usePostAxios();

	useEffect(() => {
		if (postList.length == 0) {
			getPostList()
				.then((res) => {
					setPostList(res as IPost[]);
				})
				.catch((err) => {
					console.log(err);
				});
		}
	}, []);

	return (
		<Wrapper py={20}>
			<Container flexDir="column">
				<SelectBox />
				<SimpleGrid
					columns={3}
					mx="auto"
					mt={16}
					spacing={20}
					w="100%"
					placeItems={"center"}>
					{postList.map((post) => (
						<Post key={post.id} post={post} />
					))}
				</SimpleGrid>
			</Container>
		</Wrapper>
	);
};

export default Section3;
