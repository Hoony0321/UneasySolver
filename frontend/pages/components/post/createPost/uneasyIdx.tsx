import { Flex, Icon } from "@chakra-ui/react";
import React, { useEffect, useState } from "react";
import { FaFireAlt } from "react-icons/fa";

const InputUneasyIndex = ({ setFunc }: { setFunc: (idx: number) => void }) => {
	const [uneasyIdx, setUneasyIdx] = useState<number>(0);
	const [actives, setActives] = useState<boolean[]>(
		new Array(5).fill("").map((_) => false),
	);

	useEffect(() => {
		setFunc(uneasyIdx);
	}, [uneasyIdx]);

	const handleClick = (idx: number) => {
		setActives(() => {
			setUneasyIdx(idx + 1);
			const arr = new Array(5).fill("").map((_) => false);
			for (let i = 0; i <= idx; i++) {
				arr[i] = true;
			}
			return arr;
		});
	};

	return (
		<Flex gap={6} mx="auto" justifyContent="center" mt={12}>
			{actives.map((active, idx) => (
				<Icon
					key={"uneasyActive" + idx.toString()}
					as={FaFireAlt}
					boxSize="3em"
					color="primary_light"
					className={actives[idx] ? "uneasy_idx_active" : ""}
					onClick={() => {
						handleClick(idx);
					}}
				/>
			))}
		</Flex>
	);
};

export default InputUneasyIndex;
