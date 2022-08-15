import { HamburgerIcon } from "@chakra-ui/icons";
import {
	Center,
	Circle,
	Flex,
	Link,
	Menu,
	MenuButton,
	MenuGroup,
	MenuItem,
	MenuList,
} from "@chakra-ui/react";
import { useRouter } from "next/router";
import React from "react";
import { Logo } from "../components/common/logo";
import Container from "../components/common/container";
import Wrapper from "../components/common/wrapper";

interface INavbar {
	path: string;
}

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
		<Wrapper
			as="nav"
			textAlign="center"
			py={6}
			bg="white"
			boxShadow="base"
			borderBottomWidth="1px"
			borderBottomStyle="solid"
			borderBottomColor="gray.400">
			<Container
				flexDir="row"
				justifyContent="space-between"
				alignItems="center">
				<Logo />
				<Flex gap={4} alignItems="center" fontSize={"1.2em"}>
					<MenuBox />
					<Link href="/#">새 글 쓰기</Link>
					<Link href="/#">로그인/회원가입</Link>
				</Flex>
			</Container>
		</Wrapper>
	);
};

export default Navbar;
