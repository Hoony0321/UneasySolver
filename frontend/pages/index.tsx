import { NextPage } from "next";
import React from "react";

import { Flex } from "@chakra-ui/react";
import Section1 from "./components/home/section1";
import Section2 from "./components/home/section2";

const Home: NextPage = () => {
	return (
		<Flex flexDir={"column"}>
			<Section1 />
			<Section2 />
		</Flex>
	);
};

export default Home;
