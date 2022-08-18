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
	useDisclosure,
} from "@chakra-ui/react";
import React from "react";

const AlarmModal = ({
	isOpen,
	onOpen,
	onClose,
	title,
	msg,
}: {
	isOpen: boolean;
	onOpen: () => void;
	onClose: () => void;
	title: string;
	msg: string;
}) => {
	return (
		<Modal isOpen={isOpen} onClose={onClose}>
			<ModalOverlay />
			<ModalContent>
				<ModalHeader>{title}</ModalHeader>
				<ModalCloseButton />
				<ModalBody>
					<Text>{msg}</Text>
				</ModalBody>
				<ModalFooter>
					<Button onClick={onClose}> 확인</Button>
				</ModalFooter>
			</ModalContent>
		</Modal>
	);
};

export default AlarmModal;
