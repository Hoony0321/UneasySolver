import React from "react";
import { Box, BoxProps } from "@chakra-ui/react";

type IStateBox = BoxProps;

const Wrapper = ({ children, ...props }: IStateBox) => {
	return (
		<Box w="100%" px={{ base: 12, xl: 16 }} {...props}>
			{children}
		</Box>
	);
};

export default Wrapper;
