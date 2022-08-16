/* eslint-disable react/no-children-prop */
import {
	Button,
	Flex,
	Input,
	InputGroup,
	InputLeftElement,
	SimpleGrid,
} from "@chakra-ui/react";
import React from "react";
import Container from "../common/container";
import Wrapper from "../common/wrapper";

import { ImSad, ImKey } from "react-icons/im";
import { SearchIcon } from "@chakra-ui/icons";

import { LOREM_TEXT } from "../../../src/utils/constants/string.constants";
import Post, { IPropPost } from "./post";

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

const SamplePosts = Array(11)
	.fill("")
	.map(
		(_, idx) =>
			({
				postId: idx,
				date: new Date("2022-06-14"),
				title: "불편 대상",
				content: LOREM_TEXT,
				tags: ["장소", "주차", "민원"],
				author: "작성자",
				views: 10,
				comments: 5,
			} as IPropPost),
	);

const Section3 = () => {
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
					{SamplePosts.map((post) => (
						<Post key={post.postId} post={post} />
					))}
				</SimpleGrid>
			</Container>
		</Wrapper>
	);
};

export default Section3;
