import React from "react";
import { Box, Flex, HStack, Link, Text } from "@chakra-ui/react";

interface IFooter {}

const Footer = ({ ...props }: IFooter) => {
	return (
		<Box
			as="footer"
			w="100%"
			textAlign="center"
			py={16}
			px={12}
			bg="gray.50"
			borderTopWidth="1px"
			borderTopStyle="solid"
			borderTopColor="gray.200">
			<Box w={{ base: "100%", lg: "80%" }} maxW="container.lg" m="auto">
				<HStack fontSize={14} fontWeight="bold" color="gray.600" gap={4}>
					<Link href="#">회사소개</Link>
					<Link href="#">서비스 소개</Link>
					<Link href="#">이용약관</Link>
					<Link href="#">개인정보처리방침</Link>
					<Link href="#">고객센터</Link>
				</HStack>
				<Box fontSize={12} color="gray.600" textAlign="left" mt={2}>
					<Text>
						(주)GO Easy! 대표자명 : ㅇㅇㅇ 사업자번호 : 123-45-78912
						통신판매번호 : 2022-ㅇㅇㅇㅇ-1234
					</Text>
					<Text>
						주소 : 서울특별시 강남구 테헤란로 311 이메일 : test@test.com
					</Text>
				</Box>
				<Flex
					w="100%"
					justifyContent="space-between"
					alignItems="center"
					mt={8}>
					<Flex gap={2} alignItems="center">
						<Box w="100px" h="30px" bg="gray.200">
							<Text>LOGO</Text>
						</Box>
						<Text fontSize={8} fontWeight="bold">
							by고이지(C) Law&Company Co., Ltd. ALL RIGHTS RESERVED.
						</Text>
					</Flex>
				</Flex>
			</Box>
		</Box>
	);
};

export default Footer;
