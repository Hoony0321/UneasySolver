import React from "react";
import { Box, Flex, Icon, Img, Text } from "@chakra-ui/react";
import Container from "../common/container";
import Wrapper from "../common/wrapper";
import { HiOutlineSpeakerphone } from "react-icons/hi";

const LeftElement = () => {
	return (
		<Flex flexDir="column" justifyContent="center">
			<Flex color="white">
				<Text color="white" fontWeight="bolder" fontSize="2.5em">
					나만의 불편함을 알리세요!
					<Icon as={HiOutlineSpeakerphone} w={12} h={12} color="white" />
				</Text>
			</Flex>
			<Text color="white" fontWeight="bold" fontSize="1.2em">
				멋진 해결사가 찾아옵니다.
			</Text>
		</Flex>
	);
};

const Section1 = () => {
	return (
		<Wrapper
			bgColor="primary"
			py={12}
			boxShadow="md"
			borderBottom="1px solid"
			borderColor="gray.200">
			<Container
				flexDir="row"
				alignContent="center"
				justifyContent="space-between">
				<LeftElement />
				<Img src="/Logo_white.png" />
			</Container>
		</Wrapper>
	);
};

export default Section1;
