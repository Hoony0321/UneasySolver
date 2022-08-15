import { Flex, FlexProps } from "@chakra-ui/react";
import React from "react";

type IStateFlexProps = FlexProps;

const Container = ({ children, ...props }: IStateFlexProps) => {
	return (
		<Flex maxW={"container.lg"} w="100%" mx="auto" {...props}>
			{children}
		</Flex>
	);
};

export default Container;
