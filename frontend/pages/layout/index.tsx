import React, { ReactNode } from "react";
import Head from "next/head";
import { NextRouter } from "next/router";
import Navbar from "./navbar";
import Footer from "./footer";
import { Box } from "@chakra-ui/react";
import { APP_TITLE } from "../../src/utils/constants/string.constants";

interface ILayout {
	children: ReactNode;
	router: NextRouter;
}

const Layout = ({ children, router }: ILayout) => {
	return (
		<main>
			<Head>
				<meta name="viewport" content="width=device-width" initial-scale="1" />
				<meta name="description" content="Pansori Homepage" />
				<meta name="author" content="Software Maestro 13th Go Easy! Team" />
				{/* <link rel="apple-touch-icon" href="apple-touch-icon.png" /> */}
				<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
				<meta property="og:site_name" content={APP_TITLE} />
				<meta property="og:tile" content={APP_TITLE} />
				{/* <meta property="og:type" content="website" />
				<meta property="og:image" content={} /> */}
				<title>Pansori - Homepage</title>
			</Head>

			<Navbar path={router.asPath} />
			<Box w="100%">{children}</Box>
			<Footer />
		</main>
	);
};

export default Layout;
