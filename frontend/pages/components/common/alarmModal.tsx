import {
	Button,
	Modal,
	ModalBody,
	ModalCloseButton,
	ModalContent,
	ModalFooter,
	ModalHeader,
	ModalOverlay,
	Text,
	UseDisclosureProps,
} from "@chakra-ui/react";
import Link from "next/link";
import React from "react";

export interface IModalState {
	title: string;
	msg: string;
}

export const AlarmModal = ({
	disclosure,
	state,
	href,
}: {
	disclosure: UseDisclosureProps;
	state: IModalState;
	href: string | null;
}) => {
	return (
		<Modal isOpen={disclosure.isOpen!} onClose={() => disclosure.onOpen!()}>
			<ModalOverlay />
			<ModalContent>
				<ModalHeader>{state.title}</ModalHeader>
				<ModalCloseButton />
				<ModalBody>
					<Text>{state.msg}</Text>
				</ModalBody>

				<ModalFooter>
					{href && <Link href={href}>이동</Link>}
					{!href && <Button onClick={() => disclosure.onClose!()}>닫기</Button>}
				</ModalFooter>
			</ModalContent>
		</Modal>
	);
};
