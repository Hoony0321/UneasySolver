import { atom } from "recoil";
import { ATOM_AUTHENTICATION } from "../../utils/constants/state.constants";

export interface IAuthenticationAtom {
	token: string;
	id: number;
	email: string;
	exp: number;
	auth: string;
}

const authenticationAtom = atom({
	key: `${ATOM_AUTHENTICATION}`,
	default: { token: "", id: -1, exp: -1, auth: "", email: "" },
});

export { authenticationAtom };
