import {
	Box,
	Button,
	ComponentDefaultProps,
	SimpleGrid,
	Text,
} from "@chakra-ui/react";
import { type } from "os";
import React, { useEffect, useState } from "react";
import axios from "../../../src/utils/axios";
import { useTargetAxios } from "../../../src/utils/axios/targetAxios";
import { ITarget } from "../../../src/utils/constants/@types";
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
	const { getTargetList } = useTargetAxios();
	const [targetList, setTargetList] = useState<ITarget[] | null>(null);

	useEffect(() => {
		if (targetList == null) {
			getTargetList()
				.then((res) => {
					setTargetList(res as ITarget[]);
				})
				.catch((err) => {
					console.log(err);
				});
		}
	}, []);

	return (
		<SimpleGrid columns={7} gap={6} mt={8}>
			{targetList &&
				targetList.map((target) => (
					<CategoryItem key={"target" + target.name} name={target.name} />
				))}
		</SimpleGrid>
	);
};

const Section2 = () => {
	return (
		<Wrapper pt={20}>
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
