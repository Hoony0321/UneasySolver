import React from "react";
import { AppProps } from "next/app";
import { ChakraProvider } from "@chakra-ui/react";
import Layout from "./layout";
import theme from "../src/styles/theme";
import "../src/styles/globals.css";
import { RecoilRoot } from "recoil";

function Uneasy({ Component, pageProps, router }: AppProps) {
	return (
		<RecoilRoot>
			<ChakraProvider theme={theme}>
				<Layout router={router}>
					<Component {...pageProps} key={router.route} />
				</Layout>
			</ChakraProvider>
		</RecoilRoot>
	);
}

export default Uneasy;
