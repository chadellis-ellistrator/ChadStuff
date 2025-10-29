from datetime import datetime
from typing import Dict, List
from net import Net
from payout import Payout

def create_nets(payouts: List[Payout]) -> List[Net]:
    map_nets: Dict[str, Net] = {}
    nets: List[Net] = []

    for p in payouts:
        net = map_nets.get(str(p.source.id))
        if net is None:
            net = Net(source = p.source)
            nets.append(net)
            map_nets[str(p.source.id)] = net
        net.add_payout(p)
    return nets

def submit_nets(nets: List[Net]) -> None:
    for n in nets:
        n.submit()
        
def settle_nets(nets: List[Net]) -> None:
    for n in nets:
        n.settle()

