import { HamburgerIcon } from "@chakra-ui/icons";
import {
	Box,
	Center,
	Circle,
	Flex,
	Link,
	Menu,
	MenuButton,
	MenuGroup,
	MenuItem,
	MenuList,
	Text,
} from "@chakra-ui/react";
import { useRouter } from "next/router";
import React from "react";

interface INavbar {
	path: string;
}

const Logo = () => {
	return (
		<Link href="/">
			<Flex
				w="200px"
				h="50px"
				bg="gray.100"
				alignItems="center"
				borderRadius={10}
				border="2px solid black">
				<Text fontSize={20} fontWeight="bold" mx="auto">
					LOGO
				</Text>
			</Flex>
		</Link>
	);
};

const MenuBox = () => {
	return (
		<Menu>
			<MenuButton
				as={Circle}
				size={10}
				transition="0.2s"
				_hover={{
					border: "1px solid black",
				}}>
				<Center>
					<HamburgerIcon />
				</Center>
			</MenuButton>
			<MenuList>
				<MenuGroup title="Service">
					<MenuItem>Menu 1</MenuItem>
					<MenuItem>Menu 2</MenuItem>
				</MenuGroup>
			</MenuList>
		</Menu>
	);
};

const Navbar = ({ path }: INavbar) => {
	const router = useRouter();

	return (
		<Box
			as="nav"
			w="100%"
			textAlign="center"
			bg="white"
			boxShadow="base"
			py={4}
			px={12}
			borderBottomWidth="1px"
			borderBottomStyle="solid"
			borderBottomColor="gray.200">
			<Flex
				maxW={"container.lg"}
				w={{ base: "100%", lg: "80%" }}
				m="auto"
				justifyContent="space-between"
				alignItems="center">
				<Logo />
				<Flex gap={4} alignItems="center">
					<MenuBox />
					<Link href="/login">로그인/회원가입</Link>
				</Flex>
			</Flex>
		</Box>
	);
};

export default Navbar;
