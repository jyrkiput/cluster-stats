package org.zeroturnaround.stats;

import hudson.model.Action;
import hudson.model.Queue.QueueDecisionHandler;
import hudson.model.Queue.Task;

import java.util.List;

import org.zeroturnaround.stats.model.RunStats;

public class QueingScheduler extends QueueDecisionHandler {

  @Override
  /**
   * We will store all scheduled items into an eden space. Each
   * item will get a timestamp on creation so we know when they
   * were scheduled.
   */
  public boolean shouldSchedule(Task p, List<Action> actions) {
    RunStats stats = new RunStats();
    stats.setProjectName(p.getDisplayName());

    ClusterStatisticsPlugin plugin = ClusterStatisticsPlugin.getInstance();
    plugin.getStatsData().addToEdenSpace(stats);

    return true;
  }
}