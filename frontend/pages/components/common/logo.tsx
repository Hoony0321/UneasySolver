import React from "react";
import { Text, Link } from "@chakra-ui/react";

export const Logo = () => {
	return (
		<Link href="/">
			<Text color={"primary"} fontSize={"2em"} fontWeight="bold" mx="auto">
				해결사
			</Text>
		</Link>
	);
};
