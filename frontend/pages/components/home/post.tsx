import { Box, Flex, Text } from "@chakra-ui/react";
import React from "react";
import { BsChatRight } from "react-icons/bs";
import { GoPerson } from "react-icons/go";
import { GrView } from "react-icons/gr";
import { IPost } from "../../../src/utils/constants/@types";
import { LOREM_TEXT } from "../../../src/utils/constants/string.constants";

const Post = ({
	post: { id, date, title, content, tags, author, hits, comments },
}: {
	post: IPost;
}) => {
	console.log(content);

	return (
		<Flex
			w={300}
			h={380}
			border="3px solid"
			borderColor="primary"
			rounded={10}
			flexDir="column"
			position="relative"
			px={6}
			py={8}>
			<Text pos="absolute" top={4} right={6} color="gray.500">
				{date.toString()}
			</Text>
			<Box mt={12}>
				<Text fontSize="1.5em" fontWeight="bold">
					{title}
				</Text>
				<Text className="elipsisText" w="100%" fontSize="1em" mt={6}>
					{content}
				</Text>
			</Box>

			<Box pos="absolute" bottom={6} right={6} left={6}>
				<Flex textColor="gray.500" gap={4}>
					{tags.map((tag) => (
						<Text key={id.toString() + tag}>{"#" + tag}</Text>
					))}
				</Flex>
				<Box border="1px solid" borderColor="primary" my={4} />
				<Flex justifyContent="space-between">
					<Flex alignItems="center" gap={2}>
						<GoPerson fontSize="1.3em" />
						<Text>{author}</Text>
					</Flex>
					<Flex gap={4}>
						<Flex alignItems="center" gap={2}>
							<GrView />
							<Text>{hits}</Text>
						</Flex>

						<Flex alignItems="center" gap={2}>
							<BsChatRight />
							<Text>{comments}</Text>
						</Flex>
					</Flex>
				</Flex>
			</Box>
		</Flex>
	);
};

export default Post;
