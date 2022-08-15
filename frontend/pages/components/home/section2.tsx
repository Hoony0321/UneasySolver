import {
	Box,
	Button,
	ComponentDefaultProps,
	SimpleGrid,
	Text,
} from "@chakra-ui/react";
import { type } from "os";
import React from "react";
import Container from "../common/container";
import Wrapper from "../common/wrapper";

const CategoryItem = ({ name }: { name: string }) => {
	return (
		<Button
			borderRadius="2em"
			bgColor="transparent"
			border="3px solid"
			borderColor="primary"
			boxShadow="md">
			<Text color="primary" fontSize="1.2em">
				{name}
			</Text>
		</Button>
	);
};

const Category = () => {
	return (
		<SimpleGrid columns={7} gap={6} mt={8}>
			<CategoryItem name="ITEM" />
			<CategoryItem name="ITEM" />
			<CategoryItem name="ITEM" />
			<CategoryItem name="ITEM" />
			<CategoryItem name="ITEM" />
			<CategoryItem name="ITEM" />
			<CategoryItem name="ITEM" />
			<CategoryItem name="ITEM" />
			<CategoryItem name="ITEM" />
		</SimpleGrid>
	);
};

const Section2 = () => {
	return (
		<Wrapper py={20}>
			<Container flexDir="column">
				<Text color="primary" fontWeight="bold" fontSize="1.6em">
					불편 대상
				</Text>
				<Box
					w="100%"
					border="2px solid"
					borderColor="primary"
					mt={2}
					boxShadow="base"
				/>
				<Category />
			</Container>
		</Wrapper>
	);
};

export default Section2;
