import { Text } from "@chakra-ui/react";
import { NextPage } from "next";
import React from "react";
import Container from "../components/common/container";
import Wrapper from "../components/common/wrapper";

const Mypage: NextPage = () => {
	return (
		<Wrapper>
			<Container>
				<Text> MY PAGE</Text>
			</Container>
		</Wrapper>
	);
};

export default Mypage;
