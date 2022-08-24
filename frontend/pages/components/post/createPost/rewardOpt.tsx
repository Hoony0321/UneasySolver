import { Circle, Flex, Text } from "@chakra-ui/react";
import React from "react";

const RewardOpt = ({
	text,
	point,
	active,
}: {
	text: string;
	point: number;
	active: boolean;
}) => {
	return (
		<Flex
			justifyContent="space-between"
			borderBottom="2px solid"
			borderColor={active ? "primary" : "primary_light"}
			borderRadius={2}
			color={active ? "black" : "gray.500"}
			py={2}>
			<Flex>
				<Circle size={6} bgColor={active ? "primary" : "primary_light"} />
				<Text ml={3}>{text}</Text>
			</Flex>
			<Text>+{point}</Text>
		</Flex>
	);
};

export default RewardOpt;
