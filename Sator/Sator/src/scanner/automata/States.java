/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner.automata;

/**
 *
 * @author admin
 */
public enum States implements State{
	q201(201){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return States.q201;
				case "\n": return States.q201;
				case "\t": return States.q201;
				case "\r": return States.q201;
				case "#": return States.q631;
				case "-": return States.q500;
				case "$": return States.q501;
				case "%": return States.q521;
				case "&": return States.q523;
				case "(": return FinalStates.q18;
				case ")": return FinalStates.q19;
				case "*": return States.q527;
				case ",": return States.q529;
				case ".": return FinalStates.q23;
				case "/": return States.q530;
				case ":": return States.q532;
				case "@": return States.q533;
				case "[": return States.q534;
				case "]": return States.q547;
				case "{": return States.q548;
				case "|": return States.q549;
				case "}": return States.q562;
				case "+": return States.q563;
				case "<": return States.q565;
				case "=": return States.q571;
				case ">": return States.q574;
				case "a": return States.q202;
				case "c": return States.q220;
				case "d": return States.q262;
				case "e": return States.q304;
				case "f": return States.q322;
				case "g": return States.q350;
				case "i": return States.q363;
				case "l": return States.q392;
				case "m": return States.q406;
				case "n": return States.q416;
				case "o": return States.q433;
				case "p": return States.q440;
				case "q": return States.q468;
				case "r": return States.q475;
				case "s": return States.q493;
				case "t": return States.q586;
				case "u": return States.q592;
				case "x": return States.q596;
				case "\0": return FinalStates.q143;
				case "b": return States.q601;
				case "h": return States.q601;
				case "j": return States.q601;
				case "k": return States.q601;
				case "w": return States.q601;
				case "y": return States.q601;
				case "z": return States.q601;
				case "\'": return States.q602;
				case "\"": return States.q605;
				case "0": return States.q614;
				case "1": return States.q608;
				case "2": return States.q608;
				case "3": return States.q608;
				case "4": return States.q608;
				case "5": return States.q608;
				case "6": return States.q608;
				case "7": return States.q608;
				case "8": return States.q608;
				case "9": return States.q608;
				case "v": return States.q629;
				default: return null;
			}
		}
	},
	q202(202){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q203;
				case "n": return States.q207;
				case "u": return States.q211;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q203(203){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "g": return States.q204;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q204(204){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q205;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q205(205){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q206;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q206(206){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q63;
				case "\n": return FinalStates.q63;
				case "\t": return FinalStates.q63;
				case "\r": return FinalStates.q63;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q207(207){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "n": return States.q208;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q208(208){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q209;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q209(209){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q210;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q210(210){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q64;
				case "\n": return FinalStates.q64;
				case "\t": return FinalStates.q64;
				case "\r": return FinalStates.q64;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q211(211){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "c": return States.q212;
				case "t": return States.q216;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q212;
				return null;
			}
		}
	},
	q212(212){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q213;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q213(213){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q214;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q214(214){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q215;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q215(215){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q65;
				case "\n": return FinalStates.q65;
				case "\t": return FinalStates.q65;
				case "\r": return FinalStates.q65;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q216(216){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q66;
				case "\n": return FinalStates.q66;
				case "\t": return FinalStates.q66;
				case "\r": return FinalStates.q66;
				case "=": return States.q219;
				case "e": return States.q217;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q217(217){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q218;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q218(218){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q68;
				case "\n": return FinalStates.q68;
				case "\t": return FinalStates.q68;
				case "\r": return FinalStates.q68;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q219(219){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q67;
				case "\n": return FinalStates.q67;
				case "\t": return FinalStates.q67;
				case "\r": return FinalStates.q67;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q67;
						}
					return null;
				}
			}
		},
	q220(220){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q221;
				case "e": return States.q229;
				case "l": return States.q234;
				case "o": return States.q240;
				case "u": return States.q260;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q221(221){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
                                case "p": return States.q2212;
				case "a": return States.q262;
				case "s": return States.q222;
				case "t": return States.q225;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
        q2212(2212){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q2213;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
        q2213(2213){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q2214;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
        q2214(2214){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q2215;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
        q2215(2215){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q2216;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
        q2216(2216){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q69;
				case "\n": return FinalStates.q69;
				case "\t": return FinalStates.q69;
				case "\r": return FinalStates.q69;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q222(222){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q223;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q223(223){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q224;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q224(224){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q70;
				case "\n": return FinalStates.q70;
				case "\t": return FinalStates.q70;
				case "\r": return FinalStates.q70;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q225(225){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q226;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q226(226){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "n": return States.q227;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q227(227){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q228;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q228(228){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q71;
				case "\n": return FinalStates.q71;
				case "\t": return FinalStates.q71;
				case "\r": return FinalStates.q71;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q229(229){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q230;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q230(230){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q231;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q231(231){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q232;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q232(232){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q233;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q233(233){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q73;
				case "\n": return FinalStates.q73;
				case "\t": return FinalStates.q73;
				case "\r": return FinalStates.q73;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q234(234){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q235;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q235(235){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q236;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q236(236){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "d": return States.q237;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q237(237){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q238;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q238(238){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q239;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q239(239){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q74;
				case "\n": return FinalStates.q74;
				case "\t": return FinalStates.q74;
				case "\r": return FinalStates.q74;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q240(240){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q241;
				case "n": return States.q251;
				case "r": return States.q256;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q241(241){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q242;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q242(242){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q243;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q243(243){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q244;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q244(244){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q245;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q245(245){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "b": return States.q246;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q246(246){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q247;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q247(247){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "l": return States.q248;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q248(248){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q249;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q249(249){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q250;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q250(250){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q75;
				case "\n": return FinalStates.q75;
				case "\t": return FinalStates.q75;
				case "\r": return FinalStates.q75;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q251(251){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q252;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q252(252){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q253;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q253(253){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "g": return States.q254;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q254(254){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q255;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q255(255){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q76;
				case "\n": return FinalStates.q76;
				case "\t": return FinalStates.q76;
				case "\r": return FinalStates.q76;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q256(256){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "p": return States.q257;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q257(257){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q258;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q258(258){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q259;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q259(259){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q77;
				case "\n": return FinalStates.q77;
				case "\t": return FinalStates.q77;
				case "\r": return FinalStates.q77;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q260(260){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q261;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q261(261){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q78;
				case "\n": return FinalStates.q78;
				case "\t": return FinalStates.q78;
				case "\r": return FinalStates.q78;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q262(262){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q263;
				case "i": return States.q286;
				case "u": return States.q299;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q263(263){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "c": return States.q264;
				case "t": return States.q277;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q264(264){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q265;
				case "u": return States.q273;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q265(265){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q266;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q266(266){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q267;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q267(267){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q268;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q268(268){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "n": return States.q269;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q269(269){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q270;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q270(270){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q271;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q271(271){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q272;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q272(272){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q79;
				case "\n": return FinalStates.q79;
				case "\t": return FinalStates.q79;
				case "\r": return FinalStates.q79;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q273(273){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q274;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q274(274){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q275;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q275(275){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q276;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q276(276){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q80;
				case "\n": return FinalStates.q80;
				case "\t": return FinalStates.q80;
				case "\r": return FinalStates.q80;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q277(277){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q278;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q278(278){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q279;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q279(279){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q280;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q280(280){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q281;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q281(281){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "n": return States.q282;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q282(282){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q283;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q283(283){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q284;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q284(284){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q285;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q285(285){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q81;
				case "\n": return FinalStates.q81;
				case "\t": return FinalStates.q81;
				case "\r": return FinalStates.q81;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q286(286){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q287;
				case "r": return States.q289;
				case "t": return States.q295;
				case "x": return States.q297;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q287(287){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q288;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q288(288){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q82;
				case "\n": return FinalStates.q82;
				case "\t": return FinalStates.q82;
				case "\r": return FinalStates.q82;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q289(289){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q290;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q290(290){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "c": return States.q291;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q291(291){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q292;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q292(292){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q293;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q293(293){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q294;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q294(294){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q83;
				case "\n": return FinalStates.q83;
				case "\t": return FinalStates.q83;
				case "\r": return FinalStates.q83;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q295(295){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q296;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q296(296){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q84;
				case "\n": return FinalStates.q84;
				case "\t": return FinalStates.q84;
				case "\r": return FinalStates.q84;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q297(297){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q298;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q298(298){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q85;
				case "\n": return FinalStates.q85;
				case "\t": return FinalStates.q85;
				case "\r": return FinalStates.q85;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q299(299){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q300;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q300(300){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "l": return States.q301;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q301(301){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q302;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q302(302){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q303;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q303(303){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q86;
				case "\n": return FinalStates.q86;
				case "\t": return FinalStates.q86;
				case "\r": return FinalStates.q86;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q304(304){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "f": return States.q305;
				case "s": return States.q311;
				case "t": return States.q313;
				case "x": return States.q315;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q305(305){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "f": return States.q306;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q306(306){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q307;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q307(307){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "c": return States.q308;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q308(308){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q309;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q309(309){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q310;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q310(310){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q88;
				case "\n": return FinalStates.q88;
				case "\t": return FinalStates.q88;
				case "\r": return FinalStates.q88;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q311(311){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q312;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q312(312){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q89;
				case "\n": return FinalStates.q89;
				case "\t": return FinalStates.q89;
				case "\r": return FinalStates.q89;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q313(313){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q90;
				case "\n": return FinalStates.q90;
				case "\t": return FinalStates.q90;
				case "\r": return FinalStates.q90;
				case "=": return States.q314;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q314(314){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q91;
				case "\n": return FinalStates.q91;
				case "\t": return FinalStates.q91;
				case "\r": return FinalStates.q91;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q91;
						}
					return null;
				}
			}
		},
	q315(315){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q316;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q316(316){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q317;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q317(317){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "p": return States.q318;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q318(318){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "l": return States.q319;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q319(319){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q320;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q320(320){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q321;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q321(321){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q92;
				case "\n": return FinalStates.q92;
				case "\t": return FinalStates.q92;
				case "\r": return FinalStates.q92;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q322(322){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q323;
				case "r": return States.q336;
				case "u": return States.q3410;
				case "f": return States.q620;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q323(323){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "n": return States.q324;
				case "r": return States.q327;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q324(324){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q325;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q325(325){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q326;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q326(326){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q93;
				case "\n": return FinalStates.q93;
				case "\t": return FinalStates.q93;
				case "\r": return FinalStates.q93;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q327(327){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q328;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q328(328){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q329;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q329(329){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q330;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q330(330){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q331;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q331(331){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "n": return States.q332;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q332(332){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q333;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q333(333){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q334;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q334(334){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q335;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q335(335){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q94;
				case "\n": return FinalStates.q94;
				case "\t": return FinalStates.q94;
				case "\r": return FinalStates.q94;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q336(336){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q337;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q337(337){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "c": return States.q338;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q338(338){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q339;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q339(339){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q340;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q340(340){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q341;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q341(341){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q95;
				case "\n": return FinalStates.q95;
				case "\t": return FinalStates.q95;
				case "\r": return FinalStates.q95;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q3410(3410){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q342;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q342(342){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q343;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q343(343){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "b": return States.q344;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q344(344){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q345;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q345(345){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "n": return States.q346;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q346(346){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "d": return States.q347;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q347(347){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q348;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q348(348){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q349;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q349(349){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q97;
				case "\n": return FinalStates.q97;
				case "\t": return FinalStates.q97;
				case "\r": return FinalStates.q97;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q350(350){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q351;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q351(351){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q352;
				case "e": return States.q356;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q352(352){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "d": return States.q353;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q353(353){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q354;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q354(354){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q355;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q355(355){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q98;
				case "\n": return FinalStates.q98;
				case "\t": return FinalStates.q98;
				case "\r": return FinalStates.q98;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q356(356){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "g": return States.q357;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q357(357){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q358;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q358(358){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q359;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q359(359){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q360;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q360(360){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q361;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q361(361){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q362;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q362(362){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q99;
				case "\n": return FinalStates.q99;
				case "\t": return FinalStates.q99;
				case "\r": return FinalStates.q99;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q363(363){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "g": return States.q364;
				case "m": return States.q366;
				case "n": return States.q374;
				case "t": return States.q388;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q364(364){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "n": return States.q365;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q365(365){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q102;
				case "\n": return FinalStates.q102;
				case "\t": return FinalStates.q102;
				case "\r": return FinalStates.q102;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q366(366){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q367;
				case "p": return States.q370;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q367(367){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "g": return States.q368;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q368(368){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q369;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q369(369){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q103;
				case "\n": return FinalStates.q103;
				case "\t": return FinalStates.q103;
				case "\r": return FinalStates.q103;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q370(370){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q371;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q371(371){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "n": return States.q372;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q372(372){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q373;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q373(373){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q105;
				case "\n": return FinalStates.q105;
				case "\t": return FinalStates.q105;
				case "\r": return FinalStates.q105;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q374(374){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q106;
				case "\n": return FinalStates.q106;
				case "\t": return FinalStates.q106;
				case "\r": return FinalStates.q106;
				case "c": return States.q375;
				case "i": return States.q384;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q375(375){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q376;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q376(376){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q377;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q377(377){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q378;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q378(378){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q379;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q379(379){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "n": return States.q380;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q380(380){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q381;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q381(381){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q382;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q382(382){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q383;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q383(383){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q107;
				case "\n": return FinalStates.q107;
				case "\t": return FinalStates.q107;
				case "\r": return FinalStates.q107;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q384(384){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q385;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q385(385){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q386;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q386(386){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q387;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q387(387){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q108;
				case "\n": return FinalStates.q108;
				case "\t": return FinalStates.q108;
				case "\r": return FinalStates.q108;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q388(388){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q389;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q389(389){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q390;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q390(390){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q391;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q391(391){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q109;
				case "\n": return FinalStates.q109;
				case "\t": return FinalStates.q109;
				case "\r": return FinalStates.q109;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q392(392){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q393;
				case "i": return States.q400;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q393(393){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "c": return States.q394;
				case "g": return States.q398;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q394(394){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q395;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q395(395){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q396;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q396(396){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q397;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q397(397){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q110;
				case "\n": return FinalStates.q110;
				case "\t": return FinalStates.q110;
				case "\r": return FinalStates.q110;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q398(398){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q399;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q399(399){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q111;
				case "\n": return FinalStates.q111;
				case "\t": return FinalStates.q111;
				case "\r": return FinalStates.q111;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q400(400){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "b": return States.q401;
				case "g": return States.q404;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q401(401){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q402;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q402(402){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q403;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q403(403){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q112;
				case "\n": return FinalStates.q112;
				case "\t": return FinalStates.q112;
				case "\r": return FinalStates.q112;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q404(404){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q405;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q405(405){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q113;
				case "\n": return FinalStates.q113;
				case "\t": return FinalStates.q113;
				case "\r": return FinalStates.q113;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q406(406){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q407;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q407(407){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "n": return States.q408;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q408(408){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q409;
				case "t": return States.q412;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q409(409){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q410;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q410(410){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q411;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q411(411){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q114;
				case "\n": return FinalStates.q114;
				case "\t": return FinalStates.q114;
				case "\r": return FinalStates.q114;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q412(412){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q413;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q413(413){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q414;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q414(414){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q415;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q415(415){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q115;
				case "\n": return FinalStates.q115;
				case "\t": return FinalStates.q115;
				case "\r": return FinalStates.q115;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q416(416){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q417;
				case "o": return States.q420;
				case "u": return States.q425;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q417(417){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "c": return States.q418;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q418(418){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q419;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q419(419){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q116;
				case "\n": return FinalStates.q116;
				case "\t": return FinalStates.q116;
				case "\r": return FinalStates.q116;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q420(420){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q421;
				case "n": return States.q424;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q421(421){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q422;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q422(422){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "n": return States.q423;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q423(423){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q117;
				case "\n": return FinalStates.q117;
				case "\t": return FinalStates.q117;
				case "\r": return FinalStates.q117;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q424(424){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q118;
				case "\n": return FinalStates.q118;
				case "\t": return FinalStates.q118;
				case "\r": return FinalStates.q118;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q425(425){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
                                case "n": return States.q431;
				case "m": return States.q426;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q426(426){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q427;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q427(427){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q428;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q428(428){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q429;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q429(429){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q430;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q430(430){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q119;
				case "\n": return FinalStates.q119;
				case "\t": return FinalStates.q119;
				case "\r": return FinalStates.q119;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q431(431){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "c": return States.q432;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q432(432){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q121;
				case "\n": return FinalStates.q121;
				case "\t": return FinalStates.q121;
				case "\r": return FinalStates.q121;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q433(433){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q434;
				case "p": return States.q437;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q434(434){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "d": return States.q435;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q435(435){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q436;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q436(436){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q122;
				case "\n": return FinalStates.q122;
				case "\t": return FinalStates.q122;
				case "\r": return FinalStates.q122;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q437(437){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q438;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q438(438){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q439;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q439(439){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q123;
				case "\n": return FinalStates.q123;
				case "\t": return FinalStates.q123;
				case "\r": return FinalStates.q123;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q440(440){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q441;
				case "e": return States.q453;
				case "r": return States.q460;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q441(441){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "n": return States.q442;
				case "t": return States.q445;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q442(442){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q443;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q443(443){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q444;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q444(444){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q124;
				case "\n": return FinalStates.q124;
				case "\t": return FinalStates.q124;
				case "\r": return FinalStates.q124;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q445(445){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q446;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q446(446){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "n": return States.q447;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q447(447){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q448;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q448(448){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q449;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q449(449){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "b": return States.q450;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q450(450){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q451;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q451(451){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q452;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q452(452){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q125;
				case "\n": return FinalStates.q125;
				case "\t": return FinalStates.q125;
				case "\r": return FinalStates.q125;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q453(453){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q454;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q454(454){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q126;
				case "\n": return FinalStates.q126;
				case "\t": return FinalStates.q126;
				case "\r": return FinalStates.q126;
				case "g": return States.q4530;
				case "p": return States.q455;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q4530(4530){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q4540;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q4540(4540){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q127;
				case "\n": return FinalStates.q127;
				case "\t": return FinalStates.q127;
				case "\r": return FinalStates.q127;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q455(455){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q456;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q456(456){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q457;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q457(457){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q458;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q458(458){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q459;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q459(459){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q128;
				case "\n": return FinalStates.q128;
				case "\t": return FinalStates.q128;
				case "\r": return FinalStates.q128;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q460(460){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q461;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q461(461){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "b": return States.q462;
				case "p": return States.q466;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q462(462){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q463;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q463(463){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q464;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q464(464){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q465;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q465(465){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q129;
				case "\n": return FinalStates.q129;
				case "\t": return FinalStates.q129;
				case "\r": return FinalStates.q129;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q466(466){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q467;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q467(467){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q130;
				case "\n": return FinalStates.q130;
				case "\t": return FinalStates.q130;
				case "\r": return FinalStates.q130;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q468(468){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q469;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q469(469){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q470;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q470(470){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "n": return States.q471;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q471(471){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q472;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q472(472){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q473;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q473(473){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q474;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q474(474){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q131;
				case "\n": return FinalStates.q131;
				case "\t": return FinalStates.q131;
				case "\r": return FinalStates.q131;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q475(475){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q476;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q476(476){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "d": return States.q477;
				case "s": return States.q482;
				case "v": return States.q488;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q477(477){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q478;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q478(478){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q479;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q479(479){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q480;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q480(480){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q481;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q481(481){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q132;
				case "\n": return FinalStates.q132;
				case "\t": return FinalStates.q132;
				case "\r": return FinalStates.q132;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q482(482){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "c": return States.q483;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q483(483){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q484;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q484(484){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q485;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q485(485){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "b": return States.q486;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q486(486){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q487;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q487(487){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q133;
				case "\n": return FinalStates.q133;
				case "\t": return FinalStates.q133;
				case "\r": return FinalStates.q133;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q488(488){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q489;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q489(489){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "l": return States.q490;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q490(490){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "l": return States.q491;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q491(491){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q492;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q492(492){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q134;
				case "\n": return FinalStates.q134;
				case "\t": return FinalStates.q134;
				case "\r": return FinalStates.q134;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q493(493){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "c": return States.q494;
				case "i": return States.q582;
				case "u": return States.q584;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q494(494){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q495;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q495(495){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q496;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q496(496){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "b": return States.q497;
				case "p": return States.q499;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q497(497){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "o": return States.q498;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q498(498){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q135;
				case "\n": return FinalStates.q135;
				case "\t": return FinalStates.q135;
				case "\r": return FinalStates.q135;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q499(499){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q577;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q500(500){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q0;
				case "\n": return FinalStates.q0;
				case "\t": return FinalStates.q0;
				case "\r": return FinalStates.q0;
				case "=": return States.q572;
				case ">": return States.q573;
				default: 
                                        if(input.charAt(0)>47 && input.charAt(0)<58)
                                            return States.q608;
					if (input.charAt(0)>96 && input.charAt(0)<123 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q0;
						}
					return null;
				}
			}
		},
	q501(501){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "-": return States.q502;
				case "?": return States.q503;
				case "+": return States.q504;
				case "a": return States.q505;
				case "d": return States.q5090;
				case "m": return States.q514;
				case "n": return States.q520;
				default: return null;
			}
		}
	},
	q502(502){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q1;
				case "\n": return FinalStates.q1;
				case "\t": return FinalStates.q1;
				case "\r": return FinalStates.q1;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q1;
						}
					return null;
				}
			}
		},
	q503(503){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q2;
				case "\n": return FinalStates.q2;
				case "\t": return FinalStates.q2;
				case "\r": return FinalStates.q2;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q2;
						}
					return null;
				}
			}
		},
	q504(504){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q3;
				case "\n": return FinalStates.q3;
				case "\t": return FinalStates.q3;
				case "\r": return FinalStates.q3;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q3;
						}
					return null;
				}
			}
		},
	q505(505){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "n": return States.q506;
				default: return null;
			}
		}
	},
	q506(506){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "n": return States.q507;
				default: return null;
			}
		}
	},
	q507(507){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q508;
				default: return null;
			}
		}
	},
	q508(508){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q509;
				default: return null;
			}
		}
	},
	q509(509){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q4;
				case "\n": return FinalStates.q4;
				case "\t": return FinalStates.q4;
				case "\r": return FinalStates.q4;
				case "=": return States.q5080;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q4;
						}
					return null;
				}
			}
		},
	q5080(5080){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q5;
				case "\n": return FinalStates.q5;
				case "\t": return FinalStates.q5;
				case "\r": return FinalStates.q5;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q5;
						}
					return null;
				}
			}
		},
	q5090(5090){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q6;
				case "\n": return FinalStates.q6;
				case "\t": return FinalStates.q6;
				case "\r": return FinalStates.q6;
				case "i": return States.q510;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q6;
						}
					return null;
				}
			}
		},
	q510(510){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q511;
				default: return null;
			}
		}
	},
	q511(511){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q512;
				default: return null;
			}
		}
	},
	q512(512){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q7;
				case "\n": return FinalStates.q7;
				case "\t": return FinalStates.q7;
				case "\r": return FinalStates.q7;
				case "=": return States.q513;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q7;
						}
					return null;
				}
			}
		},
	q513(513){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q8;
				case "\n": return FinalStates.q8;
				case "\t": return FinalStates.q8;
				case "\r": return FinalStates.q8;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q8;
						}
					return null;
				}
			}
		},
	q514(514){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q515;
				default: return null;
			}
		}
	},
	q515(515){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "n": return States.q516;
				default: return null;
			}
		}
	},
	q516(516){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q517;
				default: return null;
			}
		}
	},
	q517(517){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q518;
				default: return null;
			}
		}
	},
	q518(518){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q519;
				default: return null;
			}
		}
	},
	q519(519){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q9;
				case "\n": return FinalStates.q9;
				case "\t": return FinalStates.q9;
				case "\r": return FinalStates.q9;
				case "=": return States.q5190;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q9;
						}
					return null;
				}
			}
		},
	q5190(5190){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q10;
				case "\n": return FinalStates.q10;
				case "\t": return FinalStates.q10;
				case "\r": return FinalStates.q10;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q10;
						}
					return null;
				}
			}
		},
	q520(520){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q11;
				case "\n": return FinalStates.q11;
				case "\t": return FinalStates.q11;
				case "\r": return FinalStates.q11;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q11;
						}
					return null;
				}
			}
		},
	q521(521){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q12;
				case "\n": return FinalStates.q12;
				case "\t": return FinalStates.q12;
				case "\r": return FinalStates.q12;
				case "=": return States.q522;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q12;
						}
					return null;
				}
			}
		},
	q522(522){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q13;
				case "\n": return FinalStates.q13;
				case "\t": return FinalStates.q13;
				case "\r": return FinalStates.q13;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q13;
						}
					return null;
				}
			}
		},
	q523(523){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q14;
				case "\n": return FinalStates.q14;
				case "\t": return FinalStates.q14;
				case "\r": return FinalStates.q14;
				case "#": return States.q524;
				case "?": return States.q525;
				case "+": return States.q526;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q14;
						}
					return null;
				}
			}
		},
	q524(524){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q15;
				case "\n": return FinalStates.q15;
				case "\t": return FinalStates.q15;
				case "\r": return FinalStates.q15;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q15;
						}
					return null;
				}
			}
		},
	q525(525){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q16;
				case "\n": return FinalStates.q16;
				case "\t": return FinalStates.q16;
				case "\r": return FinalStates.q16;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q16;
						}
					return null;
				}
			}
		},
	q526(526){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q17;
				case "\n": return FinalStates.q17;
				case "\t": return FinalStates.q17;
				case "\r": return FinalStates.q17;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q17;
						}
					return null;
				}
			}
		},
	q527(527){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q20;
				case "\n": return FinalStates.q20;
				case "\t": return FinalStates.q20;
				case "\r": return FinalStates.q20;
				case "=": return States.q528;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q20;
						}
					return null;
				}
			}
		},
	q528(528){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q21;
				case "\n": return FinalStates.q21;
				case "\t": return FinalStates.q21;
				case "\r": return FinalStates.q21;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q21;
						}
					return null;
				}
			}
		},
	q529(529){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q22;
				case "\n": return FinalStates.q22;
				case "\t": return FinalStates.q22;
				case "\r": return FinalStates.q22;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q22;
						}
					return null;
				}
			}
		},
	q530(530){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q24;
				case "\n": return FinalStates.q24;
				case "\t": return FinalStates.q24;
				case "\r": return FinalStates.q24;
				case "=": return States.q531;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q24;
						}
					return null;
				}
			}
		},
	q531(531){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q25;
				case "\n": return FinalStates.q25;
				case "\t": return FinalStates.q25;
				case "\r": return FinalStates.q25;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q25;
						}
					return null;
				}
			}
		},
	q532(532){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q26;
				case "\n": return FinalStates.q26;
				case "\t": return FinalStates.q26;
				case "\r": return FinalStates.q26;
				case "=": return States.q619;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return FinalStates.q26;
				return null;
			}
		}
	},
	q533(533){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q28;
				case "\n": return FinalStates.q28;
				case "\t": return FinalStates.q28;
				case "\r": return FinalStates.q28;
				case "@": return States.q5330;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q28;
						}
					return null;
				}
			}
		},
	q5330(5330){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q29;
				case "\n": return FinalStates.q29;
				case "\t": return FinalStates.q29;
				case "\r": return FinalStates.q29;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q29;
						}
					return null;
				}
			}
		},
	q534(534){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q30;
				case "\n": return FinalStates.q30;
				case "\t": return FinalStates.q30;
				case "\r": return FinalStates.q30;
				case "[": Info.lookAhead=true; return FinalStates.q30;
				case "|": Info.lookAhead=true; return FinalStates.q30;
				case "(": Info.lookAhead=true; return FinalStates.q30;
				case "$": Info.lookAhead=true; return FinalStates.q30;
				case "\0": Info.lookAhead=true; return FinalStates.q30;
				case "#": return States.q535;
				case "&": return States.q538;
				case "<": return States.q541;
				case ">": return States.q544;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123){
                                                Info.lookAhead=true;
						return FinalStates.q30;
                                        }
					if (input.charAt(0)>47 && input.charAt(0)<58){
                                                Info.lookAhead=true;
                                                return FinalStates.q30;
                                        }
				return null;
			}
		}
	},
	q535(535){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "?": return States.q536;
				default: return null;
			}
		}
	},
	q536(536){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "]": return States.q537;
				default: return null;
			}
		}
	},
	q537(537){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q31;
				case "\n": return FinalStates.q31;
				case "\t": return FinalStates.q31;
				case "\r": return FinalStates.q31;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q31;
						}
					return null;
				}
			}
		},
	q538(538){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "#": Info.lookAhead=true; return FinalStates.q30;
				case "?": return States.q539;
				default: return null;
			}
		}
	},
	q539(539){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "]": return States.q540;
				default: return null;
			}
		}
	},
	q540(540){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q32;
				case "\n": return FinalStates.q32;
				case "\t": return FinalStates.q32;
				case "\r": return FinalStates.q32;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q32;
						}
					return null;
				}
			}
		},
	q541(541){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "<": return States.q542;
				default: return null;
			}
		}
	},
	q542(542){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "]": return States.q543;
				default: return null;
			}
		}
	},
	q543(543){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q33;
				case "\n": return FinalStates.q33;
				case "\t": return FinalStates.q33;
				case "\r": return FinalStates.q33;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q33;
						}
					return null;
				}
			}
		},
	q544(544){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case ">": return States.q545;
				default: return null;
			}
		}
	},
	q545(545){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "]": return States.q546;
				default: return null;
			}
		}
	},
	q546(546){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q34;
				case "\n": return FinalStates.q34;
				case "\t": return FinalStates.q34;
				case "\r": return FinalStates.q34;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q34;
						}
					return null;
				}
			}
		},
	q547(547){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q35;
				case "\n": return FinalStates.q35;
				case "\t": return FinalStates.q35;
				case "\r": return FinalStates.q35;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q35;
						}
					return null;
				}
			}
		},
	q548(548){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q36;
				case "\n": return FinalStates.q36;
				case "\t": return FinalStates.q36;
				case "\r": return FinalStates.q36;
				case "\"": Info.lookAhead=true; return FinalStates.q36;
				case "-": Info.lookAhead=true; return FinalStates.q36;
				case "{": Info.lookAhead=true; return FinalStates.q36;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return FinalStates.q36;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return FinalStates.q36;
				return null;
			}
		}
	},
	q549(549){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "-": return States.q550;
				case "*": return States.q551;
				case "/": return States.q553;
				case "^": return States.q555;
				case "|": return States.q5550;
				case "+": return States.q557;
				case "<": return States.q559;
				case ">": return States.q561;
				default: return null;
			}
		}
	},
	q550(550){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q37;
				case "\n": return FinalStates.q37;
				case "\t": return FinalStates.q37;
				case "\r": return FinalStates.q37;
				case "=": return States.q560;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q37;
						}
					return null;
				}
			}
		},
	q551(551){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q38;
				case "\n": return FinalStates.q38;
				case "\t": return FinalStates.q38;
				case "\r": return FinalStates.q38;
				case "=": return States.q552;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q38;
						}
					return null;
				}
			}
		},
	q552(552){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q39;
				case "\n": return FinalStates.q39;
				case "\t": return FinalStates.q39;
				case "\r": return FinalStates.q39;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q39;
						}
					return null;
				}
			}
		},
	q553(553){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q40;
				case "\n": return FinalStates.q40;
				case "\t": return FinalStates.q40;
				case "\r": return FinalStates.q40;
				case "=": return States.q554;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q40;
						}
					return null;
				}
			}
		},
	q554(554){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q41;
				case "\n": return FinalStates.q41;
				case "\t": return FinalStates.q41;
				case "\r": return FinalStates.q41;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q41;
						}
					return null;
				}
			}
		},
	q555(555){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q42;
				case "\n": return FinalStates.q42;
				case "\t": return FinalStates.q42;
				case "\r": return FinalStates.q42;
				case "=": return States.q556;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q42;
						}
					return null;
				}
			}
		},
	q556(556){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q43;
				case "\n": return FinalStates.q43;
				case "\t": return FinalStates.q43;
				case "\r": return FinalStates.q43;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q43;
						}
					return null;
				}
			}
		},
	q5550(5550){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q44;
				case "\n": return FinalStates.q44;
				case "\t": return FinalStates.q44;
				case "\r": return FinalStates.q44;
				case "=": return States.q5560;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q44;
						}
					return null;
				}
			}
		},
	q5560(5560){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q45;
				case "\n": return FinalStates.q45;
				case "\t": return FinalStates.q45;
				case "\r": return FinalStates.q45;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q45;
						}
					return null;
				}
			}
		},
	q557(557){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q46;
				case "\n": return FinalStates.q46;
				case "\t": return FinalStates.q46;
				case "\r": return FinalStates.q46;
				case "=": return States.q558;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q46;
						}
					return null;
				}
			}
		},
	q558(558){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q47;
				case "\n": return FinalStates.q47;
				case "\t": return FinalStates.q47;
				case "\r": return FinalStates.q47;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q47;
						}
					return null;
				}
			}
		},
	q559(559){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q48;
				case "\n": return FinalStates.q48;
				case "\t": return FinalStates.q48;
				case "\r": return FinalStates.q48;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q48;
						}
					return null;
				}
			}
		},
	q560(560){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q49;
				case "\n": return FinalStates.q49;
				case "\t": return FinalStates.q49;
				case "\r": return FinalStates.q49;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q49;
						}
					return null;
				}
			}
		},
	q561(561){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q50;
				case "\n": return FinalStates.q50;
				case "\t": return FinalStates.q50;
				case "\r": return FinalStates.q50;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q50;
						}
					return null;
				}
			}
		},
	q562(562){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q51;
				case "\n": return FinalStates.q51;
				case "\t": return FinalStates.q51;
				case "\r": return FinalStates.q51;
				case ",": Info.lookAhead=true; return FinalStates.q51;
				case "}": Info.lookAhead=true; return FinalStates.q51;
				default: return null;
			}
		}
	},
	q563(563){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q52;
				case "\n": return FinalStates.q52;
				case "\t": return FinalStates.q52;
				case "\r": return FinalStates.q52;
				case "=": return States.q564;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q52;
						}
					return null;
				}
			}
		},
	q564(564){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q53;
				case "\n": return FinalStates.q53;
				case "\t": return FinalStates.q53;
				case "\r": return FinalStates.q53;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q53;
						}
					return null;
				}
			}
		},
	q565(565){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "#": return States.q566;
				case " ": return FinalStates.q54;
				case "\n": return FinalStates.q54;
				case "\t": return FinalStates.q54;
				case "\r": return FinalStates.q54;
				case "-": return States.q569;
				case "=": return States.q570;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q54;
						}
					return null;
				}
			}
		},
	q566(566){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "#": return States.q568;
				default: 
					if (ALPHABET.replaceAll("[#]","").contains(input))
						return States.q567;
				return null;
			}
		}
	},
	q567(567){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "#": return States.q568;
				default: 
					//if (ALPHABET.replaceAll("[#]","").contains(input))
						return States.q567;
				//return null;
			}
		}
	},
	q568(568){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case ">": return States.q201;
				default: 
					if (ALPHABET.replaceAll("[>]","").contains(input))
						return States.q567;
				return null;
			}
		}
	},
	q569(569){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q55;
				case "\n": return FinalStates.q55;
				case "\t": return FinalStates.q55;
				case "\r": return FinalStates.q55;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q55;
						}
					return null;
				}
			}
		},
	q570(570){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q56;
				case "\n": return FinalStates.q56;
				case "\t": return FinalStates.q56;
				case "\r": return FinalStates.q56;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q56;
						}
					return null;
				}
			}
		},
	q571(571){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q57;
				case "\n": return FinalStates.q57;
				case "\t": return FinalStates.q57;
				case "\r": return FinalStates.q57;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q57;
						}
					return null;
				}
			}
		},
	q572(572){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q58;
				case "\n": return FinalStates.q58;
				case "\t": return FinalStates.q58;
				case "\r": return FinalStates.q58;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q58;
						}
					return null;
				}
			}
		},
	q573(573){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q60;
				case "\n": return FinalStates.q60;
				case "\t": return FinalStates.q60;
				case "\r": return FinalStates.q60;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q60;
						}
					return null;
				}
			}
		},
	q574(574){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q59;
				case "\n": return FinalStates.q59;
				case "\t": return FinalStates.q59;
				case "\r": return FinalStates.q59;
				case "<": return States.q575;
				case "=": return States.q576;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q59;
						}
					return null;
				}
			}
		},
	q575(575){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q61;
				case "\n": return FinalStates.q61;
				case "\t": return FinalStates.q61;
				case "\r": return FinalStates.q61;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q61;
						}
					return null;
				}
			}
		},
	q576(576){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q62;
				case "\n": return FinalStates.q62;
				case "\t": return FinalStates.q62;
				case "\r": return FinalStates.q62;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q62;
						}
					return null;
				}
			}
		},
	q577(577){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q578;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q578(578){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q579;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q579(579){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q580;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q580(580){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q581;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q581(581){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q136;
				case "\n": return FinalStates.q136;
				case "\t": return FinalStates.q136;
				case "\r": return FinalStates.q136;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q582(582){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "g": return States.q583;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q583(583){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "l": return States.q5840;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q5840(5840){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q5830;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q5830(5830){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q137;
				case "\n": return FinalStates.q137;
				case "\t": return FinalStates.q137;
				case "\r": return FinalStates.q137;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q584(584){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q585;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q585(585){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q138;
				case "\n": return FinalStates.q138;
				case "\t": return FinalStates.q138;
				case "\r": return FinalStates.q138;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q586(586){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q587;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q587(587){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "m": return States.q588;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q588(588){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "p": return States.q589;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q589(589){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q590;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q590(590){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q591;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q591(591){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q139;
				case "\n": return FinalStates.q139;
				case "\t": return FinalStates.q139;
				case "\r": return FinalStates.q139;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q592(592){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q593;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q593(593){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "q": return States.q594;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q594(594){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q595;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q595(595){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q5950;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q5950(5950){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q140;
				case "\n": return FinalStates.q140;
				case "\t": return FinalStates.q140;
				case "\r": return FinalStates.q140;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q596(596){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q597;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q597(597){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q598;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q598(598){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q599;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q599(599){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q141;
				case "\n": return FinalStates.q141;
				case "\t": return FinalStates.q141;
				case "\r": return FinalStates.q141;
				case "=": return States.q600;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q600(600){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q142;
				case "\n": return FinalStates.q142;
				case "\t": return FinalStates.q142;
				case "\r": return FinalStates.q142;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q142;
						}
					return null;
				}
			}
		},
	q601(601){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q101;
				case "\n": return FinalStates.q101;
				case "\t": return FinalStates.q101;
				case "\r": return FinalStates.q101;
				case "[": Info.lookAhead=true; return FinalStates.q101;
				case "&": Info.lookAhead=true; return FinalStates.q101;
				case "|": Info.lookAhead=true; return FinalStates.q101;
				case "(":  Info.lookAhead=true; return FinalStates.q101;
                                case ")":  Info.lookAhead=true; return FinalStates.q101;
				case "$": Info.lookAhead=true; return FinalStates.q101;
				case "\0": Info.lookAhead=true; return FinalStates.q101;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q602(602){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "\\": return States.q603;
				default: 
					if (ALPHABET.replaceAll("[\'\"]","").contains(input))
						return States.q604;
				return null;
			}
		}
	},
	q603(603){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q604;
				case "n": return States.q604;
				case "r": return States.q604;
				case "\'": return States.q604;
				case "\"": return States.q604;
				default: return null;
			}
		}
	},
	q604(604){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "\'": return FinalStates.q104;
				default: return null;
			}
		}
	},
	q605(605){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "\\": return States.q606;
				case "\"": return FinalStates.q72;
				default: 
					if (ALPHABET.replaceAll("[\'\"]","").contains(input))
						return States.q607;
				return null;
			}
		}
	},
	q606(606){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "t": return States.q607;
				case "n": return States.q607;
				case "r": return States.q607;
				case "\'": return States.q607;
				case "\"": return States.q607;
				default: return null;
			}
		}
	},
	q607(607){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
                                case "\\": return States.q606;
				case "\"": return FinalStates.q72;
				default: 
					if (ALPHABET.replaceAll("[\'\"]","").contains(input))
						return States.q607;
				return null;
			}
		}
	},
	q608(608){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "|": return States.q609;
				case "$": return States.q611;
				case " ": return FinalStates.q120;
				case "\n": return FinalStates.q120;
				case "\t": return FinalStates.q120;
				case "\r": return FinalStates.q120;
				case "[": Info.lookAhead=true; return FinalStates.q120;
				case "&": Info.lookAhead=true; return FinalStates.q120;
				case "(": Info.lookAhead=true; return FinalStates.q120;
				case "\0": Info.lookAhead=true; return FinalStates.q120;
				default: 
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q608;
				return null;
			}
		}
	},
	q609(609){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "-": Info.lookAhead=true; return FinalStates.q120;
				case "*": Info.lookAhead=true; return FinalStates.q120;
				case "/": Info.lookAhead=true; return FinalStates.q120;
				case "^": Info.lookAhead=true; return FinalStates.q120;
				case "|": Info.lookAhead=true; return FinalStates.q120;
				case "<": Info.lookAhead=true; return FinalStates.q120;
				case ">": Info.lookAhead=true; return FinalStates.q120;
				default: 
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q610;
				return null;
			}
		}
	},
	q610(610){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q96;
				case "\n": return FinalStates.q96;
				case "\t": return FinalStates.q96;
				case "\r": return FinalStates.q96;
				case "[": Info.lookAhead=true; return FinalStates.q96;
				case "&": Info.lookAhead=true; return FinalStates.q96;
				case "|": Info.lookAhead=true; return FinalStates.q96;
				case "(": Info.lookAhead=true; return FinalStates.q96;
				case "$": Info.lookAhead=true; return FinalStates.q96;
				case "\0": Info.lookAhead=true; return FinalStates.q96;
				default: 
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q610;
				return null;
			}
		}
	},
	q611(611){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "-": Info.lookAhead=true; return FinalStates.q120;
				case "?": Info.lookAhead=true; return FinalStates.q120;
				case "+": Info.lookAhead=true; return FinalStates.q120;
				case "a": Info.lookAhead=true; return FinalStates.q120;
				case "d": Info.lookAhead=true; return FinalStates.q120;
				case "m": Info.lookAhead=true; return FinalStates.q120;
				case "n": Info.lookAhead=true; return FinalStates.q120;
				default: 
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q6110;
				return null;
			}
		}
	},
	q6110(6110){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "$": return States.q612;
				default: 
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q6110;
				return null;
			}
		}
	},
	q612(612){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				default: 
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q613;
				return null;
			}
		}
	},
	q613(613){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q100;
				case "\n": return FinalStates.q100;
				case "\t": return FinalStates.q100;
				case "\r": return FinalStates.q100;
				case "[": Info.lookAhead=true; return FinalStates.q100;
				case "&": Info.lookAhead=true; return FinalStates.q100;
				case "|": Info.lookAhead=true; return FinalStates.q100;
				case "(": Info.lookAhead=true; return FinalStates.q100;
				case "$": Info.lookAhead=true; return FinalStates.q100;
				case "\0": Info.lookAhead=true; return FinalStates.q100;
				default: 
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q613;
				return null;
			}
		}
	},
	q614(614){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q615;
				case "x": return States.q617;
                                //case "|": return States.q609;
				//case "$": return States.q611;
				case " ": return FinalStates.q120;
				case "\n": return FinalStates.q120;
				case "\t": return FinalStates.q120;
				case "\r": return FinalStates.q120;
				case "[": Info.lookAhead=true; return FinalStates.q72;
				case "&": Info.lookAhead=true; return FinalStates.q72;
				case "(": Info.lookAhead=true; return FinalStates.q72;
				case "\0": Info.lookAhead=true; return FinalStates.q72;
				default: return null;
			}
		}
	},
	q615(615){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q616;
				case "v": return States.q616;
				case "x": return States.q616;
				case "l": return States.q616;
				case "c": return States.q616;
				case "d": return States.q616;
				case "m": return States.q616;
				default: return null;
			}
		}
	},
	q616(616){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q616;
				case "v": return States.q616;
				case "x": return States.q616;
				case "l": return States.q616;
				case "c": return States.q616;
				case "d": return States.q616;
				case "m": return States.q616;
				case " ": return FinalStates.q120;
				case "\n": return FinalStates.q120;
				case "\t": return FinalStates.q120;
				case "\r": return FinalStates.q120;
				case "[": Info.lookAhead=true; return FinalStates.q120;
				case "&": Info.lookAhead=true; return FinalStates.q120;
				case "|": Info.lookAhead=true; return FinalStates.q120;
				case "(": Info.lookAhead=true; return FinalStates.q120;
				case "$": Info.lookAhead=true; return FinalStates.q120;
				case "\0": Info.lookAhead=true; return FinalStates.q120;
				default: return null;
			}
		}
	},
	q617(617){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q618;
				case "b": return States.q618;
				case "c": return States.q618;
				case "d": return States.q618;
				case "e": return States.q618;
				case "f": return States.q618;
				default: 
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q618;
				return null;
			}
		}
	},
	q618(618){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "a": return States.q618;
				case "b": return States.q618;
				case "c": return States.q618;
				case "d": return States.q618;
				case "e": return States.q618;
				case "f": return States.q618;
				case " ": return FinalStates.q120;
				case "\n": return FinalStates.q120;
				case "\t": return FinalStates.q120;
				case "\r": return FinalStates.q120;
				case "[": Info.lookAhead=true; return FinalStates.q120;
				case "&": Info.lookAhead=true; return FinalStates.q120;
				case "|": Info.lookAhead=true; return FinalStates.q120;
				case "(": Info.lookAhead=true; return FinalStates.q120;
				case "$": Info.lookAhead=true; return FinalStates.q120;
				case "\0": Info.lookAhead=true; return FinalStates.q120;
				default: 
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q618;
				return null;
			}
		}
	},
	q619(619){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q27;
				case "\n": return FinalStates.q27;
				case "\t": return FinalStates.q27;
				case "\r": return FinalStates.q27;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123 || input.charAt(0)>47 && input.charAt(0)<58 || NEXT.contains(input)){
						Info.lookAhead = true;
							return FinalStates.q27;
						}
					return null;
				}
			}
		},
	q620(620){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "l": return States.q621;
				default: return null;
			}
		}
	},
	q621(621){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q622;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q622(622){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q623;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q623(623){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "d": return States.q624;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q624(624){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "i": return States.q625;
				default: return null;
			}
		}
	},
	q625(625){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "c": return States.q626;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q626(626){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "u": return States.q627;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q627(627){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "s": return States.q628;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q628(628){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case " ": return FinalStates.q87;
				case "\n": return FinalStates.q87;
				case "\t": return FinalStates.q87;
				case "\r": return FinalStates.q87;
				case "[": Info.lookAhead=true; return FinalStates.q87;
				case "&": Info.lookAhead=true; return FinalStates.q87;
				case "|": Info.lookAhead=true; return FinalStates.q87;
				case "(": Info.lookAhead=true; return FinalStates.q87;
				case "$": Info.lookAhead=true; return FinalStates.q87;
				case "\0": Info.lookAhead=true; return FinalStates.q87;
				default: 
					if (input.charAt(0)>96 && input.charAt(0)<123)
						return States.q601;
				
					if (input.charAt(0)>47 && input.charAt(0)<58)
						return States.q601;
				return null;
			}
		}
	},
	q629(629){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "e": return States.q630;
				default: return null;
			}
		}
	},
	q630(630){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "r": return States.q622;
				default: return null;
			}
		}
	},
	q631(631){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "#": return States.q632;
				default: return null;
			}
		}
	},
	q632(632){
		@Override
		public State next(String input){
			Info.lookAhead=false;
			switch(input){
				case "\n": return States.q201;
				default: 
					if (ALPHABET.replaceAll("[\n]","").contains(input))
						return States.q632;
				return null;
			}
		}
	},
        q633(633){
            @Override
            public State next(String input){
                return null;
            }
        };
	public int code;
	
	private States(int code){
		this.code=code;
		
	}
	@Override
	public abstract State next(String input);
	@Override
	public int getCode(){
		return this.code;
	}
	@Override
	public boolean hasLookedAhead(){
            boolean b = Info.lookAhead;
            return Info.lookAhead;
	}
	@Override
	public boolean isFinal(){return false;}
        
        
}