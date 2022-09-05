import { HamburgerIcon } from "@chakra-ui/icons";
import {
	Box,
	Button,
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
import React, { useEffect, useRef, useState } from "react";
import { Logo } from "../components/common/logo";
import Container from "../components/common/container";
import Wrapper from "../components/common/wrapper";
import { jwtUtils } from "../../src/utils/jwtUtils";
import { useRecoilState } from "recoil";
import { authenticationAtom } from "../../src/store/authentication/authentication.state";
import { useAuthenticationActions } from "../../src/store/authentication/authentication.action";
import { useAuthAxios } from "../../src/utils/axios/authAxios";

interface INavbar {
	path: string;
}

const Navbar = ({ path }: INavbar) => {
	const { refreshInfo } = useAuthenticationActions();
	const router = useRouter();
	const [isLogin, setIsLogin] = useState<boolean>();
	const [authState, setAuthState] = useRecoilState(authenticationAtom);
	const { getAccessAuth } = useAuthAxios();

	const logout = () => {
		localStorage.clear();
		setAuthState((prev) => ({ ...prev, token: "" }));
	};

	const onClickLogOut = () => {
		logout();
		location.replace("/");
	};

	const onClickMypage = async () => {
		await getAccessAuth(authState.token).then(async (res) => {
			console.log("인증 결과 : ", res);
			if (res) {
				await router.push("/members/mypage");
			} else {
				await router.push("/login");
				logout();
			}
		});
	};

	useEffect(() => {
		console.log("첫 렌더링 실행");
		refreshInfo();
	}, []);

	useEffect(() => {
		console.log("authState 변경됨.");
		console.log(authState.token);
		setIsLogin(authState.token != "");
	}, [authState]);

	const MenuBox = ({ isLogin }: { isLogin: boolean }) => {
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
						{isLogin && <MenuItem onClick={onClickLogOut}>로그아웃</MenuItem>}
						{isLogin && (
							<MenuItem
								onClick={() => {
									onClickMypage()
										.then((resolved) => {
											console.log(resolved);
										})
										.catch((error) => {
											console.log(error);
										});
								}}>
								마이페이지
							</MenuItem>
						)}
					</MenuGroup>
				</MenuList>
			</Menu>
		);
	};

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
				{isLogin && (
					<Flex alignItems="center" gap={8} fontSize={"1.2em"} color="primary">
						<Link href="/members/post/createPost">새 글 쓰기</Link>
						<Flex
							border="2px solid"
							borderColor="primary"
							borderRadius="2em"
							gap={2}
							pl={4}
							pr={2}
							alignItems="center"
							justifyContent="space-evenly">
							<Text>{authState.nickname}</Text>
							<MenuBox isLogin={isLogin} />
						</Flex>
					</Flex>
				)}

				{!isLogin && (
					<Flex gap={4} alignItems="center" fontSize={"1.2em"} color="primary">
						<MenuBox isLogin={isLogin!} />
						<Link href="/login">로그인/회원가입</Link>
					</Flex>
				)}
			</Container>
		</Wrapper>
	);
};

export default Navbar;
