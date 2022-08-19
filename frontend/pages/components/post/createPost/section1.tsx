import { Circle, Flex, Text } from "@chakra-ui/react";
import React from "react";

const Section1 = () => {
	return (
		<Flex>
			<Flex flexDir="row">
				<Circle>1</Circle>
				<Text>불편 대상을 선택해주세요.</Text>
			</Flex>
		</Flex>
	);
};

export default Section1;
