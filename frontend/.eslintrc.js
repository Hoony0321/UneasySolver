module.exports = {
	env: {
		browser: true,
		es2021: true,
		jest: true,
		es6 : true
	},
	extends: [
		"eslint:recommended",
		"next/core-web-vitals",
		"plugin:react/recommended",
		"plugin:react-hooks/recommended",
		"plugin:@typescript-eslint/eslint-recommended",
		"plugin:@typescript-eslint/recommended",
		"plugin:@typescript-eslint/recommended-requiring-type-checking",
	],
	settings: {
		react: {
			version: "detect",
		},
	},
	parser: "@typescript-eslint/parser",
	parserOptions: {
		ecmaFeatures: {
			jsx: true,
		},
		ecmaVersion: 2018,
		sourceType: "module",
		project: ["./tsconfig.json"],
	},
	plugins: ["react", "react-hooks", "@typescript-eslint", "prettier"],
	rules: {
		indent: "off",
		"prettier/prettier": "error",
		"linebreak-style": ["error", "unix"],
		"arrow-parens": ["error", "always"],
		quotes: ["error", "double"],
		semi: ["error", "always"],
		"react/prop-types": "off",
		"@typescript-eslint/no-empty-interface": "warn",
		"@typescript-eslint/no-empty-function": "warn",
	},
};
