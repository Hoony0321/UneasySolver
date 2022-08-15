import React from "react";
import { Box, Flex, HStack, Link, Text } from "@chakra-ui/react";
import Wrapper from "../components/common/wrapper";
import Container from "../components/common/container";

interface IFooter {}

const Footer = ({ ...props }: IFooter) => {
	return (
		<Wrapper
			as="footer"
			py={16}
			bg="gray.50"
			borderTopWidth="1px"
			borderTopStyle="solid"
			borderTopColor="gray.200">
			<Container
				flexDir={{ base: "column", md: "row" }}
				justifyContent={{ base: "center", md: "space-between" }}
				alignItems="center"
				gap={6}>
				<Box>
					<Text fontSize={"1em"}>All About Inconvenience...</Text>
					<Text fontSize={"1.2em"} fontWeight="bold">
						세상의 모든 불편함을 위한, 해결사
					</Text>
				</Box>

				<Box>
					<Text fontSize={"1.2em"} fontWeight="bold">
						Company
					</Text>
					<Text fontSize={"0.8em"}>(주)해결사 대표 : 고대훈</Text>
					<Text fontSize={"0.8em"}>사업자번호 : 010-1234-5678</Text>
					<Text fontSize={"0.8em"}>
						서울특별시 강남구 삼성로 119길 56(삼성동)
					</Text>
					<Text fontSize={"0.8em"}>
						Inconvenience Solver all right reserved
					</Text>
				</Box>

				<Box>
					<Text fontSize={"1.2em"} fontWeight="bold">
						CS Center
					</Text>
					<Text fontSize={"0.8em"}>02-1234-5678</Text>
					<Text fontSize={"0.8em"}>Email : eogns0321@gmail.com</Text>
					<Text fontSize={"0.8em"}>Fax: 02-123-4567</Text>
					<Text fontSize={"0.8em"}>10:00 ~ 21:00 365일 연중무휴</Text>
				</Box>
			</Container>
		</Wrapper>
	);
};

export default Footer;
